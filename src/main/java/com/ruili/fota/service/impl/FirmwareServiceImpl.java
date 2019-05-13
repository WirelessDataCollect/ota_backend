package com.ruili.fota.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.ruili.fota.common.utils.Md5Tools;
import com.ruili.fota.common.utils.UUIDTools;
import com.ruili.fota.constant.LoadStatusEnum;
import com.ruili.fota.constant.MongoDBEnum;
import com.ruili.fota.dao.bo.ConfigBO;
import com.ruili.fota.dao.bo.ConfigResPO;
import com.ruili.fota.dao.bo.LoadProcessBO;
import com.ruili.fota.netty.pk.ConfigPK;
import com.ruili.fota.dao.entity.FotaProcessEntity;
import com.ruili.fota.dao.mapper.FotaImagesMapper;
import com.ruili.fota.dao.mapper.FotaLoadHistoryMapper;
import com.ruili.fota.dao.mapper.FotaLoadersMapper;
import com.ruili.fota.dao.po.FotaImages;
import com.ruili.fota.dao.po.FotaLoadHistory;
import com.ruili.fota.netty.FotaProcessMap;
import com.ruili.fota.netty.NettyChannelMap;
import com.ruili.fota.netty.pk.FirmCheckPK;
import com.ruili.fota.service.FirmwareService;
import com.ruili.fota.service.MongoService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.socket.SocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@Service
public class FirmwareServiceImpl implements FirmwareService {

    Md5Tools md5Tools = new Md5Tools();

    @Autowired
    private FotaImagesMapper fotaImagesMapper;

    @Autowired
    private FotaLoadHistoryMapper fotaLoadHistoryMapper;

    @Autowired
    private FotaLoadersMapper fotaLoadersMapper;

    @Autowired
    private MongoService mongoService;

    //每个包的分格数量
    private static int packageSegmentation = 50;

    //每次去数据库IO流取的字节数
    private static int eachBatch = 1024;

    @Override
    public int insertFirmwareInfo(FotaImages fotaImages) {
        return fotaImagesMapper.insert(fotaImages);
    }

    @Override
    public ConfigResPO configDownloadPatten(ConfigBO configBO) throws IOException {
        //先检查设备的在线状态，若状态为非在线，则返回false，若设备在线，返回true并开始下发。
        SocketChannel socketChannel = NettyChannelMap.get(configBO.getImei());
        ConfigResPO resPO = new ConfigResPO();
        if (socketChannel == null) {
            resPO.setLoadStatusEnum(LoadStatusEnum.CONFIG_DEVICE_DISCONNECT);
            return resPO;
        }
        //读取设备的固件信息
        GridFSDBFile file = mongoService.selectGridFS(MongoDBEnum.GridFSBucket_FIRMWARE.getGridFSBucket(), configBO.getFirmwareId());
        if (file == null) {
            resPO.setLoadStatusEnum(LoadStatusEnum.LOAD_FIRMWARE_NOT_FOUND);
            return resPO;
        }
        BufferedInputStream reader = new BufferedInputStream(file.getInputStream(), 8192);
        byte[] buffer = new byte[eachBatch];
        ByteBuf responseBuf = socketChannel.alloc().buffer((int) file.getLength() * 4);
        while (-1 != reader.read(buffer)) {
            responseBuf.writeBytes(buffer);
        }
        //将下载固件上下文存储进Map中
        int totalPackNum = (int) (file.getLength() / (eachBatch * packageSegmentation));
        //生成唯一的请求id
        String requestId = UUIDTools.getUUID32();
        resPO.setRequestId(requestId);
        FotaProcessMap.initStateFotaProcessEntity(configBO.getImei(), requestId, totalPackNum, responseBuf, configBO);
        ConfigPK configPK = new ConfigPK(configBO.getRecID(), configBO.getSendID(), configBO.getImei(), configBO.getCannum(), configBO.getMesaure(), totalPackNum);
        try {
            //下发配置包给设备
            socketChannel.writeAndFlush(getWriteBuf(configPK, socketChannel));
            resPO.setLoadStatusEnum(LoadStatusEnum.CONFIG_SUCCESS);
            return resPO;
        } catch (Exception e) {
            System.out.println(e);
            resPO.setLoadStatusEnum(LoadStatusEnum.CONFIG_ERROR);
            return resPO;
        }
    }

    @Override
    public void downloadFirmware(String imei, int packNum) throws NoSuchAlgorithmException {
        FotaProcessEntity entity = FotaProcessMap.get(imei);
        SocketChannel socketChannel = NettyChannelMap.get(imei);
        //获取到对应的包
        ByteBuf sliceByteBuf = entity.getFirmwareByteBuf().copy(packNum * eachBatch * packageSegmentation, eachBatch * packageSegmentation);
        //在下发前计算MD5值
        FirmCheckPK checkPK = new FirmCheckPK(md5Tools.getMD5(sliceByteBuf.copy()), packNum);
        //下发固件信息
        socketChannel.writeAndFlush(sliceByteBuf);
        //下发固件校验包
        socketChannel.writeAndFlush(getWriteBuf(checkPK.toString(), socketChannel));
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
    public LoadProcessBO downloadFirmwareReport(String imei, String requestId) {
        //直接返回数据库中设备的当前状态
        FotaProcessEntity entity = FotaProcessMap.get(imei);
        //如果设备的升级过程还在进行，则在Map中取出升级状态
        if (entity!=null){
            LoadProcessBO processBO = new LoadProcessBO(entity);
            return processBO;
        }else {

            return entity;
        }

    }


    @Override
    public List<FotaLoadHistory> queryLoadHistory(String imei, String beginTime, String endTime) {
        Example queryLoadHistoryExample = new Example(FotaLoadHistory.class);
        Example.Criteria criteria = queryLoadHistoryExample.createCriteria();
        if (beginTime != null || beginTime != "") {
            criteria.andGreaterThanOrEqualTo("gmtCreate", beginTime);
        }
        if (endTime != null || endTime != "") {
            criteria.andLessThanOrEqualTo("gmtCreate", endTime);
        }
        if (imei != null || imei != "") {
            criteria.andEqualTo("imei", imei);
        }
        List<FotaLoadHistory> fotaLoadHistoryList = fotaLoadHistoryMapper.selectByExample(queryLoadHistoryExample);
        return fotaLoadHistoryList;
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

}
