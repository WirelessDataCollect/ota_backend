package com.ruili.fota.controller;

import com.alibaba.fastjson.JSONObject;

import com.ruili.fota.constant.UserTypeEnum;
import com.ruili.fota.constant.result.BaseResp;
import com.ruili.fota.constant.result.ResultStatus;
import com.ruili.fota.meta.bo.ConfigBO;
import com.ruili.fota.meta.bo.ConfigResPO;
import com.ruili.fota.meta.bo.LoadProcessBO;
import com.ruili.fota.meta.po.FotaRole;
import com.ruili.fota.meta.po.FotaUsers;
import com.ruili.fota.meta.vo.OtaFileVO;
import com.ruili.fota.service.AccountService;
import com.ruili.fota.service.AuthorityService;
import com.ruili.fota.service.FirmwareService;
import com.ruili.fota.service.MongoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author: liangjingxiong
 * @date: 2019-04-09
 * @description: 此API接口为固件管理相关的接口，请求注意全局路由firmware
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/firmware")
public class FirmwareController extends BaseController {

    private static String urlPrefix = "OtaFile";

    @Autowired
    private MongoService mongoService;
    @Autowired
    private FirmwareService firmwareService;
    @Autowired
    private AuthorityService authorityService;


    @ApiOperation(value = "固件 查询 固件列表", tags = {"固件管理"}, notes = "进入固件文件管理时，查询固件列表")
    @ApiImplicitParams({
    })
    @PostMapping(value = "/query")
    public BaseResp<List<OtaFileVO>> firmwareQuery(@RequestParam("userName") String userName) {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }
        String currentUser = this.findCurrentUser().getUsername();
        List<FotaRole> roles = authorityService.getRoleByUser(userName);
        for (FotaRole role : roles) {
            if (role.getValue().equals(UserTypeEnum.ADMIN.getType())) {
                return new BaseResp(ResultStatus.SUCCESS, firmwareService.queryFirmwareImages(userName));
            }
        }
        return new BaseResp(ResultStatus.SUCCESS, firmwareService.queryFirmwareImages(currentUser));
    }

    @ApiOperation(value = "固件 上传 上传信息", tags = {"固件管理"}, notes = "上传固件版本号、固件对应设备类型、上传人，备注，返回插入条数以及响应状态")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "firmwareId", value = "固件唯一id", required = true),
        @ApiImplicitParam(name = "mcuType", value = "mcu类型，单通道、双通道、中央控制器", required = true),
        @ApiImplicitParam(name = "fileName", value = "固件文件名，上传成功后获得回调", required = true),
        @ApiImplicitParam(name = "firmwareVersion", value = "固件版本号", required = true),
        @ApiImplicitParam(name = "content", value = "固件备注", required = true),
    })
    @PostMapping(value = "/uploadinfo")
    public BaseResp uploadInfo(@RequestParam("firmwareId") String firmwareId,
        @RequestParam("mcuType") String mcuType,
        @RequestParam("fileName") String fileName,
        @RequestParam("firmwareVersion") String firmwareVersion,
        @RequestParam("content") String content) {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

        FotaUsers currentUser = this.findCurrentUser();
        return new BaseResp(ResultStatus.SUCCESS,
            firmwareService.insertFirmwareInfo(firmwareId, mcuType, fileName, firmwareVersion, content, currentUser));
    }

    @ApiOperation(value = "固件 上传 上传文件", tags = {"固件管理"}, notes = "上传固件，返回固件唯一32位id")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "file", value = "file文件内容"),
    })
    @PostMapping(value = "/upload")
    public BaseResp firmUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

        String firmwareId = mongoService.insertFirmwareAndGetImgId(file);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firmwareId", firmwareId);
        return new BaseResp(ResultStatus.SUCCESS, "返回固件唯一id", jsonObject);
    }

    @ApiOperation(value = "固件 删除 删除文件", tags = {"固件管理"}, notes = "删除固件，根据固件唯一firmwareId")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "firmwareId", value = "固件唯一id，firmwareId"),
    })
    @PostMapping(value = "/deletebyfirmwareid")
    public BaseResp firmDelete(@RequestParam("firmwareIds") List<String> firmwareIds)
        throws NotFoundException {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

        String tenantId = this.findCurrentUser().getUsername();
        return new BaseResp(ResultStatus.SUCCESS, "删除成功",
            firmwareService.batchDeleteFirmInfoByFirmId(firmwareIds, tenantId));
    }

    @ApiOperation(value = "固件 下载 配置", tags = {"固件烧录"}, notes = "烧录固件之前下发配置给设备，后端自动开始烧录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "RecID", value = "接收id", required = true),
        @ApiImplicitParam(name = "SendID", value = "发送id", required = true),
        @ApiImplicitParam(name = "imei", value = "imei", required = true),
        @ApiImplicitParam(name = "cannum", value = "can接口", required = true),
        @ApiImplicitParam(name = "measure", value = "升级方法，离线或在线，离线23，在线66，离线进度结束即完成，在线还要等升级完成", required = true),
        @ApiImplicitParam(name = "firmwareId", value = "固件id", required = true),
        @ApiImplicitParam(name = "mcuType", value = "mcu类型", required = true),
    })
    @PostMapping(value = "/config")
    public BaseResp<ConfigResPO> downloadFireware(ConfigBO configBO) throws IOException, NotFoundException {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

        //找到固件信息
        String tenantId = this.findCurrentUser().getUsername();
        configBO.setFotaImages(firmwareService.selectImageByImageId(configBO.getFirmwareId(), tenantId));
        return new BaseResp(ResultStatus.SUCCESS, firmwareService.configDownloadPatten(configBO, tenantId));
    }

    @ApiOperation(value = "固件 下载 下载查询", tags = {"固件烧录"}, notes = "查询目前烧录的进度")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "imei", value = "升级设备的imei", required = true)
    })
    @PostMapping(value = "/downloadreport")
    public BaseResp<LoadProcessBO> downloadFirewareReport(@RequestParam("imei") String imei,
        @RequestParam("requestId") String requestId) throws NotFoundException, IOException, ClassNotFoundException {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

        String tenantId = this.findCurrentUser().getUsername();
        return new BaseResp(ResultStatus.SUCCESS, firmwareService.downloadFirmwareReport(imei, requestId, tenantId));
    }

}
