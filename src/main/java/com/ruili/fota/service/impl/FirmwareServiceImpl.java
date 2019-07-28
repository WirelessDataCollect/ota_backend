package com.ruili.fota.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.mongodb.gridfs.GridFSDBFile;
import com.ruili.fota.common.CopyTools;
import com.ruili.fota.common.DateTools;
import com.ruili.fota.common.Md5Tools;
import com.ruili.fota.common.UUIDTools;
import com.ruili.fota.constant.DownloadPattern;
import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.constant.MongoDBEnum;
import com.ruili.fota.meta.bo.ConfigBO;
import com.ruili.fota.meta.bo.ConfigResPO;
import com.ruili.fota.meta.bo.LoadProcessBO;
import com.ruili.fota.meta.po.FotaImages;
import com.ruili.fota.meta.po.FotaLoadHistory;
import com.ruili.fota.meta.po.FotaUsers;
import com.ruili.fota.meta.vo.OtaFileVO;
import com.ruili.fota.netty.FirmwareBufMap;
import com.ruili.fota.netty.pk.ConfigPK;
import com.ruili.fota.meta.entity.FotaProcessEntity;
import com.ruili.fota.mapper.FotaImagesMapper;
import com.ruili.fota.mapper.FotaLoadHistoryMapper;
import com.ruili.fota.mapper.FotaLoadersMapper;
import com.ruili.fota.netty.FotaProcessMap;
import com.ruili.fota.netty.NettyChannelMap;
import com.ruili.fota.netty.pk.FirmCheckPK;
import com.ruili.fota.service.FirmwareService;
import com.ruili.fota.service.LoadDeviceManageService;
import com.ruili.fota.service.MongoService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.socket.SocketChannel;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FirmwareServiceImpl implements FirmwareService {

    @Autowired
    private FotaImagesMapper fotaImagesMapper;

    @Autowired
    private FotaLoadHistoryMapper fotaLoadHistoryMapper;

    @Autowired
    private FotaLoadersMapper fotaLoadersMapper;

    @Autowired
    private LoadDeviceManageService loadDeviceManageService;

    @Autowired
    private MongoService mongoService;

    @Autowired
    private DownloadPattern downloadPattern;

    @Override
    public List<OtaFileVO> queryFirmwareImages(String tenantId) {
        List<OtaFileVO> otaFileVOList = new ArrayList<>();
        Example example = new Example(FotaImages.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(tenantId)) {
            criteria.andLike("uploader", "%" + tenantId + "%");
        }
        List<FotaImages> fotaImagesList = fotaImagesMapper.selectByExample(example);
        for (FotaImages images : fotaImagesList) {
            otaFileVOList.add(new OtaFileVO(images));
        }
        return otaFileVOList;
    }

    @Override
    public int insertFirmwareInfo(String firmwareId, String mcuType, String fileName, String firmwareVersion,
        String content, FotaUsers currentUser) {
        FotaImages fotaImages = new FotaImages(null, firmwareId, mcuType, fileName, currentUser.getRealname(),
            currentUser.getPhone(), firmwareVersion, content, DateTools.currentTime(), DateTools.currentTime());
        return fotaImagesMapper.insert(fotaImages);
    }

    @Override
    public ConfigResPO configDownloadPatten(ConfigBO configBO, String tenantId) throws IOException, NotFoundException {

        //判断设备是否已经在OTA过程中
        if (FotaProcessMap.get(configBO.getImei()) != null) {
            throw new NotFoundException("设备已经在OTA序列当中，可能由他人操作，请勿重复操作");
        }

        //每个包的分格数量
        int packageSegmentation = downloadPattern.packageSegmentation;
        //每次去数据库IO流取的字节数
        int eachBatch = downloadPattern.eachBatch;

        //先检查设备的在线状态，若状态为非在线，则返回false，若设备在线，返回true并开始下发。
        SocketChannel socketChannel = NettyChannelMap.get(configBO.getImei());
        ConfigResPO resPO = new ConfigResPO();
        if (socketChannel == null) {
            resPO.setLoadStatusEnum(LoadStatusEnum.CONFIG_DEVICE_DISCONNECT);
            return resPO;
        }
        //读取设备的固件信息
        GridFSDBFile file = mongoService.selectGridFS(MongoDBEnum.GridFSBucket_FIRMWARE.getGridFSBucket(),
            configBO.getFirmwareId());
        if (file == null) {
            resPO.setLoadStatusEnum(LoadStatusEnum.LOAD_FIRMWARE_NOT_FOUND);
            return resPO;
        }
        BufferedInputStream reader = new BufferedInputStream(file.getInputStream(), 8192);
        byte[] buffer = new byte[eachBatch];
        ByteBuf responseBuf = socketChannel.alloc().buffer((int)file.getLength() * 4);
        while (-1 != reader.read(buffer)) {
            responseBuf.writeBytes(buffer);
        }
        //将固件存储进固件的map中
        FirmwareBufMap.add(configBO.getImei(), responseBuf);
        //将下载固件上下文存储进Map中
        int totalPackNum = (int)(file.getLength() / (eachBatch * packageSegmentation));
        //生成唯一的请求id，并更新到设备表中
        String requestId = UUIDTools.getUUID32();
        resPO.setRequestId(requestId);
        if (loadDeviceManageService.updateRequestIdByImei(configBO.getImei(), requestId) != 1) {
            throw new NotFoundException("更新设备requestId失败");
        }
        //判断是否已经存在升级过程中，实现配置重复下发不多开资源
        if (FotaProcessMap.get(configBO.getImei()) == null) {
            FotaProcessMap.initStateFotaProcessEntity(configBO.getImei(), requestId, configBO.getFirmwareId(),
                totalPackNum, configBO, tenantId);
        } else {
            if (FotaProcessMap.getByTenantId(configBO.getImei(), tenantId) == null) {
                //资源被其他租户占用，驳回本次下发请求
                return ConfigResPO.builder().loadStatusEnum(LoadStatusEnum.DEVICE_IS_IN_USING_BY_OTHERS).build();
            }
            //删除旧的配置项，采用新的配置项
            FotaProcessMap.updateFotaProcessEntity(configBO.getImei(), requestId, configBO.getFirmwareId(),
                totalPackNum, configBO, tenantId);
        }
        System.out.println(configBO);
        ConfigPK configPK = new ConfigPK(convertByteStr2Int(configBO.getRecID()),
            convertByteStr2Int(configBO.getSendID()), configBO.getImei(), configBO.getCannum(), configBO.getMeasure(),
            totalPackNum);
        try {
            //下发配置包给设备
            socketChannel.writeAndFlush(getWriteBuf(configPK, socketChannel));
            resPO.setLoadStatusEnum(LoadStatusEnum.CONFIG_NO_STATUS);
            return resPO;
        } catch (Exception e) {
            System.out.println(e);
            resPO.setLoadStatusEnum(LoadStatusEnum.CONFIG_ERROR);
            return resPO;
        }
    }

    @Override
    public void downloadFirmware(String imei, int packNum) throws NoSuchAlgorithmException {

        //每个包的分格数量
        int packageSegmentation = downloadPattern.packageSegmentation;
        //每次去数据库IO流取的字节数
        int eachBatch = downloadPattern.eachBatch;

        FotaProcessEntity entity = FotaProcessMap.get(imei);
        entity.setStatusEnum(LoadStatusEnum.LOAD_ON_STATUS);
        SocketChannel socketChannel = NettyChannelMap.get(imei);
        //获取到对应的包
        ByteBuf sliceByteBuf;
        if (packNum != entity.getTotalPack()) {
            //当前不是最后一个包
            sliceByteBuf = FirmwareBufMap.get(imei).copy(packNum * eachBatch * packageSegmentation,
                eachBatch * packageSegmentation);
        } else {
            //当前是最后一个包
            sliceByteBuf = FirmwareBufMap.get(imei).copy(packNum * eachBatch * packageSegmentation,
                FirmwareBufMap.get(imei).readableBytes() % (eachBatch * packageSegmentation));
        }
        //在下发前计算MD5值
        FirmCheckPK checkPK = new FirmCheckPK(Md5Tools.getMD5(sliceByteBuf.copy()), packNum);
        //下发固件信息
        int len = sliceByteBuf.copy().readableBytes();
        socketChannel.writeAndFlush(sliceByteBuf);
        System.out.println("已下发设备固件数据：" + len + "字节");
        //下发固件校验包
        socketChannel.writeAndFlush(getWriteBuf(checkPK.toString(), socketChannel));
        System.out.println("已下发md5校验包");
        entity.setPackNumber(packNum);
        //判断是否烧写完毕在设备上报烧写完成包后
    }

    /**
     * 需要判断设备是否在FotaMap中，如果不在则可能已经更新成功或者失败，查询数据库
     *
     * @param imei
     * @return
     */

    @Override
    public LoadProcessBO downloadFirmwareReport(String imei, String requestId, String tenantId)
        throws NotFoundException {
        //对设备的升级状态进行copy，防止由于多线程处理产生的不一致的情况
        //        FotaProcessEntity entity = CopyTools.deepClone(FotaProcessMap.get(imei));
        FotaProcessEntity entity = FotaProcessMap.get(imei);
        //如果设备的升级过程还在进行，则在Map中取出升级状态
        if (entity != null) {
            //如果查询不是当前租户的信息，则返回越权信息
            if (!entity.getTenantId().equals(tenantId)) {
                throw new NotFoundException("查询用户与当前升级队列资源权限用户不一致，无法查询");
            }
            //判断下发配置包是否成功
            if (entity.getConfigTime() != null) {
                //如果超过10s，将前端查询结果变为配置失败，同时清除资源
                if (DateTools.currentTime().getTime() - entity.getConfigTime().getTime() > 10000) {
                    entity.setStatusEnum(LoadStatusEnum.CONFIG_ERROR);
                    System.out.println(entity.getStatusEnum());
                    LoadProcessBO processBO = new LoadProcessBO(entity);
                    return processBO;
                }
            }
            //如果已经过了配置期
            LoadProcessBO processBO = new LoadProcessBO(entity);
            System.out.println(entity.getStatusEnum());
            return processBO;
        } else {
            System.out.println("开始查询历史记录");
            Example example = new Example(FotaLoadHistory.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("requestId", requestId);
            criteria.andEqualTo("tenantId", tenantId);
            FotaLoadHistory history = fotaLoadHistoryMapper.selectOneByExample(example);
            //TODO 这边history不停在做序列化和映射，需要对比下和历史查询的关系
            if (history == null) { throw new NotFoundException("设备未在升级且未查询到设备升级记录"); }
            LoadProcessBO processBO = new LoadProcessBO();
            ConfigBO configBO = JSON.parseObject(history.getConfigBo(), ConfigBO.class);
            JSONObject loadProcess = JSON.parseObject(history.getLoadProcess());
            processBO.setStatusEnum(LoadStatusEnum.searchByCode(loadProcess.getInteger("code")));
            processBO.setConfigBO(configBO);
            System.out.println(processBO);
            return processBO;
        }
    }

    @Override
    @Transactional
    public boolean batchDeleteFirmInfoByFirmId(List<String> firmwareIds, String tenantId) throws NotFoundException {
        //检查租户对这些固件的所有权
        Example example = new Example(FotaImages.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("firmwareId", firmwareIds);
        criteria.andEqualTo("uploader", tenantId);
        List<FotaImages> fotaImages = fotaImagesMapper.selectByExample(example);
        if (fotaImages.size() != firmwareIds.size()) {
            throw new NotFoundException("租户删除不属于自己的固件，此次请求无效");
        }
        fotaImagesMapper.deleteByExample(example);
        mongoService.deleteFirmwareByImgIds(firmwareIds);
        return true;
    }

    @Override
    public FotaImages selectImageByImageId(String imageId, String tenantId) {
        Example example = new Example(FotaImages.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("firmwareId", imageId);
        criteria.andEqualTo("uploader", tenantId);
        return fotaImagesMapper.selectOneByExample(example);
    }

    @Override
    public FotaImages selectImageByImageIdForWatcher(String imageId) {
        Example example = new Example(FotaImages.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("firmwareId", imageId);
        return fotaImagesMapper.selectOneByExample(example);
    }

    public <T> ByteBuf getWriteBuf(T res, SocketChannel channel) {
        String resString = JSON.toJSONString(res);
        System.out.println(resString);
        ByteBuf responseBuf = channel.alloc().buffer(4 * resString.length());
        responseBuf.writeBytes(resString.getBytes());
        return responseBuf;
    }

    public ByteBuf getWriteBuf(String res, SocketChannel channel) {
        ByteBuf responseBuf = channel.alloc().buffer(4 * res.length());
        responseBuf.writeBytes(res.getBytes());
        return responseBuf;
    }

    /**
     * 完成前端发送16进制String转化为10进制的int，下发给前端
     */
    private static int convertByteStr2Int(String port) {
        return Integer.parseInt(port, 16);
    }

}
