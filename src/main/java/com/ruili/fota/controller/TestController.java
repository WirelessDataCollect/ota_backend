package com.ruili.fota.controller;

import com.ruili.fota.constant.result.BaseResp;
import com.ruili.fota.constant.result.ResultStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController extends BaseController{
    @ApiOperation(value = "健康测试", tags = {"健康测试"}, notes = "健康返回msg='healthy'，可以作为ping测试接口")
    @GetMapping(value = "/healthy")
    public BaseResp healthy() {
        return new BaseResp(ResultStatus.SUCCESS, "healthy", "when you see 'healthy', it means the backend server is healthy");
    }

    @DeleteMapping(value = "/delete")
    public BaseResp delete(@RequestParam("param") String param) {
        return new BaseResp(ResultStatus.SUCCESS, "delete", param);
    }
}
