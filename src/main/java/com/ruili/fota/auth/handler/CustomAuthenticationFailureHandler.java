package com.ruili.fota.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruili.fota.constant.result.BaseResp;
import com.ruili.fota.constant.result.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yin on 2019/2/11
 */
@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class.getName());

    @Autowired
    private ObjectMapper objectMapper;

    public CustomAuthenticationFailureHandler() {
        logger.info("CustomAuthenticationFailureHandler loading ...");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        BaseResp result = new BaseResp();

        if (exception instanceof BadCredentialsException) {
            result.setStatus(ResultStatus.pwd_not_exist.getStatus());
            result.setMessage(ResultStatus.pwd_not_exist.getMsg());
        } else if (exception instanceof UsernameNotFoundException) {
            result.setStatus(ResultStatus.user_not_exist.getStatus());
            result.setMessage(ResultStatus.user_not_exist.getMsg());
        } else {
            result.setStatus(ResultStatus.FAIL.getStatus());
            result.setMessage("登陆失败！");
        }

        //exception：认证过程中的错误
        logger.error("Authentication does not pass");

        int status = HttpStatus.OK.value();
        response.setStatus(status);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }


}