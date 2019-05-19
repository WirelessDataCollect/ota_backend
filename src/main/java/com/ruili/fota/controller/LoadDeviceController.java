package com.ruili.fota.controller;

import com.ruili.fota.constant.result.BaseResp;
import com.ruili.fota.constant.result.ResultStatus;
import com.ruili.fota.service.LoadDeviceManageService;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/device")
public class LoadDeviceController {

    @Autowired
    private LoadDeviceManageService loadDeviceManageService;

    @ApiOperation(value = "设备 信息 信息查询", tags = {"设备查询"}, notes = "查看设备的基本信息")
    @ApiImplicitParams({
    })
    @PostMapping(value = "/query")
    public BaseResp deviceQuery() {
        return new BaseResp(ResultStatus.SUCCESS, loadDeviceManageService.queryDeviceList());
    }


}
