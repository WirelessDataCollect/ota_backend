package com.ruili.fota.auth.endpoint;

import com.ruili.fota.constant.result.BaseResp;
import com.ruili.fota.constant.result.ResultStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yin on 2019/2/11
 */
@FrameworkEndpoint
@RestController
public class RevokeTokenEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(RevokeTokenEndpoint.class.getName());

    @Autowired
    @Lazy
    private ConsumerTokenServices consumerTokenServices;

    @ApiOperation(value = "登出", tags = {"登陆管理"},
            notes = "登出使得token失效，登陆路径/login，需要携带以下参数：username,password,type=user," +
                    "grant_type=password，后面两个不强制，并需要在header中添加参数authorization='Basic d2ViQXBwOndlYkFwcA=='")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "登陆颁发的access_token", required = true)
    })
    @DeleteMapping(value = "/oauth/token")
    public BaseResp revokeToken(@RequestParam("token") String token) {
        BaseResp result = new BaseResp();
        logger.error(token);
        if (consumerTokenServices.revokeToken(token)) {

            result.setStatus(ResultStatus.SUCCESS.getStatus());
            result.setMessage("注销成功");
        } else {
            result.setStatus(ResultStatus.FAIL.getStatus());
            result.setMessage("注销失败");
        }

        return result;
    }
}
