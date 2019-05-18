package com.ruili.fota.controller;

import com.ruili.fota.constant.result.BaseResp;
import com.ruili.fota.constant.result.ResultStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TestController {
    @ApiOperation(value = "健康测试", tags = {"健康测试"}, notes = "健康返回msg='healthy'，可以作为ping测试接口")
    @GetMapping(value = "/healthy")
    public BaseResp healthy() {
        return new BaseResp(ResultStatus.SUCCESS, "healthy", "");
    }
}
