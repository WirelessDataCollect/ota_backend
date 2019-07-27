package com.ruili.fota.controller;

import com.ruili.fota.constant.result.BaseResp;
import com.ruili.fota.constant.result.ResultStatus;
import com.ruili.fota.meta.vo.DeviceVO;
import com.ruili.fota.service.LoadDeviceManageService;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/device")
public class LoadDeviceController extends BaseController {

    private static String urlPrefix = "deviceManage";

    @Autowired
    private LoadDeviceManageService loadDeviceManageService;

    @ApiOperation(value = "设备 列表 信息查询", tags = {"设备查询"}, notes = "查看设备的列表")
    @ApiImplicitParams({
    })
    @PostMapping(value = "/query")
    public BaseResp<List<DeviceVO>> deviceQuery() {
        if (!checkPermission(urlPrefix)) {
            return new BaseResp(ResultStatus.http_status_unauthorized, "此用户无访问该接口权限，请联系管理员");
        }

        return new BaseResp(ResultStatus.SUCCESS, loadDeviceManageService.queryDeviceList());
    }
}
