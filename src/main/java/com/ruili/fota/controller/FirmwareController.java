package com.ruili.fota.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruili.fota.constant.result.BaseResp;
import com.ruili.fota.constant.result.ResultStatus;
import com.ruili.fota.meta.bo.ConfigBO;
import com.ruili.fota.mapper.FotaImagesMapper;
import com.ruili.fota.meta.bo.ConfigResPO;
import com.ruili.fota.meta.bo.LoadProcessBO;
import com.ruili.fota.meta.po.FotaImages;
import com.ruili.fota.meta.vo.OtaFileVO;
import com.ruili.fota.service.FirmwareService;
import com.ruili.fota.service.MongoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
public class FirmwareController {
    @Autowired
    private MongoService mongoService;
    @Autowired
    private FirmwareService firmwareService;

    @ApiOperation(value = "固件 查询 固件列表", tags = {"固件管理"}, notes = "进入固件文件管理时，查询固件列表")
    @ApiImplicitParams({
    })
    @PostMapping(value = "/query")
    public BaseResp<List<OtaFileVO>> firmwareQuery() {
        return new BaseResp(ResultStatus.SUCCESS, firmwareService.queryFirmwareImages());
    }

    @ApiOperation(value = "固件 上传 上传信息", tags = {"固件管理"}, notes = "上传固件版本号、固件对应设备类型、上传人，备注，返回插入条数以及响应状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "firmwareId", value = "固件唯一id", required = true),
            @ApiImplicitParam(name = "uploadUserName", value = "上传者姓名"),
            @ApiImplicitParam(name = "uploadUserPhoneNumber", value = "上传者电话"),
            @ApiImplicitParam(name = "firmwareVersion", value = "固件版本号"),
            @ApiImplicitParam(name = "content", value = "固件备注"),
    })
    @PostMapping(value = "/uploadinfo")
    public BaseResp uploadInfo(FotaImages fotaImages) {
        return new BaseResp(ResultStatus.SUCCESS, firmwareService.insertFirmwareInfo(fotaImages));
    }

    @ApiOperation(value = "固件 上传 上传文件", tags = {"固件管理"}, notes = "上传固件，返回固件唯一32位id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "file文件内容"),
    })
    @PostMapping(value = "/upload")
    public BaseResp firmUpload(@RequestParam("file") MultipartFile file) throws IOException {
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
    public BaseResp firmDelete(@RequestParam("firmwareId") String firmwareId) throws IOException {
        return new BaseResp(ResultStatus.SUCCESS, "删除成功", mongoService.deleteFirmwareByImgId(firmwareId));
    }

    @ApiOperation(value = "固件 下载 配置", tags = {"固件烧录"}, notes = "烧录固件之前下发配置给设备，后端自动开始烧录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "RecID", value = "接收id", required = true),
            @ApiImplicitParam(name = "SendID", value = "发送id", required = true),
            @ApiImplicitParam(name = "imei", value = "imei", required = true),
            @ApiImplicitParam(name = "cannum", value = "can接口", required = true),
            @ApiImplicitParam(name = "measure", value = "升级方法，离线或在线", required = true),
            @ApiImplicitParam(name = "firmwareId", value = "固件id", required = true),
            @ApiImplicitParam(name = "mcuType", value = "mcu类型", required = true),
    })
    //TODO 需要修改接口的内容
    @PostMapping(value = "/config")
    public BaseResp<ConfigResPO> downloadFireware(ConfigBO configBO) throws IOException {
        return new BaseResp(ResultStatus.SUCCESS, firmwareService.configDownloadPatten(configBO));
    }


    @ApiOperation(value = "固件 下载 下载查询", tags = {"固件烧录"}, notes = "查询目前烧录的进度")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imei", value = "升级设备的imei", required = true)
    })
    @PostMapping(value = "/downloadreport")
    public BaseResp<LoadProcessBO> downloadFireware(@RequestParam("imei") String imei,
                                                    @RequestParam("requestId") String requestId) {
        return new BaseResp(ResultStatus.SUCCESS, firmwareService.downloadFirmwareReport(imei, requestId));
    }

}
