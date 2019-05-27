package com.ruili.fota.auth.handler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruili.fota.constant.AuthorityEnum;
import com.ruili.fota.constant.result.BaseResp;
import com.ruili.fota.constant.result.ResultStatus;
import com.ruili.fota.meta.po.FotaUsers;
import com.ruili.fota.service.AccountService;
import com.ruili.fota.service.AuthorityService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//import org.springframework.security.oauth2.provider.TokenRequest;

/**
 * Created by Yin on 2019/2/11
 */
@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class.getName());

    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        //获取登陆信息
        String header = request.getHeader("Authorization");
        String loginType = request.getParameter("type");
        FotaUsers currentUser = accountService.findUserByUsername(authentication.getName());

        //没有client信息
        if (header == null || !header.startsWith("Basic ")) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }

        //base64解码获取clientId、clientSecret
        String[] tokens = extractAndDecodeHeader(header, request);
        assert tokens.length == 2;
        String clientId = tokens[0];
        String clientSecret = tokens[1];
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在，clientId:" + clientId);
        } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配，clientId:" + clientId);
        }

        //添加登陆日志
//        Log log = new Log();
//        log.setType(LOG_TYPE_USERLOGIN);
//        log.setUserId(currentUser.getId());
//        log.setUsername(currentUser.getUsername());
//        log.setIpAddress(getIpAddress(request));
//        log.setTitle("登录");
//        logService.addLog(log);

        JSONObject jsonObject = generateToken(authentication, clientId, clientDetails);

        //跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new BaseResp(ResultStatus.SUCCESS, getLoginTokenInfo(jsonObject, currentUser))));


    }

    /**
     * 向登陆的 token 存入一些info信息
     *
     * @param jsonObject
     * @return
     */
    private JSONObject getLoginTokenInfo(JSONObject jsonObject, FotaUsers currentUser) {
        jsonObject.put("userId", currentUser.getGid());
        jsonObject.put("realname", currentUser.getRealname());
        jsonObject.put("menu", authorityService.getMenuByUser(AuthorityEnum.MENU_TYPE_PC.getType(), currentUser.getGid()));
        return jsonObject;
    }

    /**
     * base64解码请求头 Basic aW1vb2M6aW1vb2NzZWNyZXQ=
     * Decodes the header into a username and password.
     *
     * @throws BadCredentialsException if the Basic header is not present or is not valid
     *                                 Base64
     */
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request)
            throws IOException {
        //Basic aW1vb2M6aW1vb2NzZWNyZXQ= 截取Basic后的
        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            //解码后格式   用户名:密码
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(
                    "Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");

        int delim = token.indexOf(":");

        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        //返回的数组是   [用户名(就是client_id),clientSecret] 其实就是配置的
        /**
         * security.oauth2.client.clientId = imooc
         security.oauth2.client.clientSecret = imoocsecret
         */
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }
    /**
     * 生成token
     *
     * @param authentication
     * @return
     * @throws JsonProcessingException
     */
    private JSONObject generateToken(Authentication authentication, String clientId, ClientDetails clientDetails) throws JsonProcessingException {

        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");

        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

        OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

        String accessTokenStr = objectMapper.writeValueAsString(accessToken);
        JSONObject jsonObject = JSON.parseObject(accessTokenStr);

        return jsonObject;
    }

}
