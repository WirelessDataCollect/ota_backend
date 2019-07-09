package com.ruili.fota.auth.filter;

import com.ruili.fota.auth.token.MyAuthenticationToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yin on 2019/2/11
 */
public class MyLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * 登陆类型
     */
    private static final String SPRING_SECURITY_RESTFUL_TYPE_KEY = "type";

    public static final String SPRING_SECURITY_RESTFUL_TYPE_WE_CHAT = "weChat";
    public static final String SPRING_SECURITY_RESTFUL_TYPE_PHONE = "phone";
    public static final String SPRING_SECURITY_RESTFUL_TYPE_DEFAULT = "user";

    /**
     * 微信登陆
     */
    private static final String SPRING_SECURITY_RESTFUL_ENCRYPTEDDATA_KEY = "encryptedData";
    private static final String SPRING_SECURITY_RESTFUL_IV_KEY = "iv";
    private static final String SPRING_SECURITY_RESTFUL_CODE_KEY = "js_code";

    /**
     * 手机登陆 手机号 验证码
     */
    private static final String SPRING_SECURITY_RESTFUL_MOBILE_KEY = "mobile";
    private static final String SPRING_SECURITY_RESTFUL_IDENTIFYCODE_KEY = "identifyCode";
    //private static final String SPRING_SECURITY_RESTFUL_WeChat_KEY = "openId";//小程序不能直接获取openid

    /**
     * 账户登陆 账户名 密码
     */
    private static final String SPRING_SECURITY_RESTFUL_USERNAME_KEY = "username";
    private static final String SPRING_SECURITY_RESTFUL_PASSWORD_KEY = "password";


    private static final String SPRING_SECURITY_RESTFUL_LOGIN_URL = "/login";


    public MyLoginAuthenticationFilter() {
        super(new AntPathRequestMatcher(SPRING_SECURITY_RESTFUL_LOGIN_URL, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        String type = obtainParameter(request, SPRING_SECURITY_RESTFUL_TYPE_KEY);
        String mobile = obtainParameter(request, SPRING_SECURITY_RESTFUL_MOBILE_KEY);
        MyAuthenticationToken authRequest;
        String principal;
        String credentials;

        //微信公众号登录
        if (SPRING_SECURITY_RESTFUL_TYPE_WE_CHAT.equals(type)) {
//            String encryptedData = obtainParameter(request, SPRING_SECURITY_RESTFUL_ENCRYPTEDDATA_KEY).replace(" ", "+");
////            String encryptedData1 = "9CRJ8cSkFabf2hksdNFUFn9tddTALkAoq+0yKR0jhsWU0YGOqeTSuVqBtSzPm3FObkk/NThmdE2tqNNSTYTc2Eh5X2i9eNdP2TaDw77phUsmTmmS/hYib4nTSRM9kMV6RTdC8nN5UAzJSFzbn6y2LM3khODBOZDx221OuOXr9l7QslLmUKXwTpTtwBItDLnyQTpnrNufylbT7ko7PxwFvZjeOSdw6zHr6TCpGQ6UcKiE/MX/u7R16HxGY5Lq4eX6Dcx0BdRofFqmHy9CvM+2gIqjC0oomftkELA68haB9BYkX2pH2PmHvn/oCG6lH+M/y0H6oeO5aDzMVcrGyj2xvkJbH7NLbhgyq6DJptVtpgR1F7fko/pf25oSBAxKOjQ4RIhbjiebMnkT9dGnntdF70cgUh6B2pk2xSPgRzXDQz2HYMkf+OnFQkGAJ0qFjKRj";
//            String iv = obtainParameter(request, SPRING_SECURITY_RESTFUL_IV_KEY);
//            String code = obtainParameter(request, SPRING_SECURITY_RESTFUL_CODE_KEY);
//
//            Map map = WXTools.doLogin1(encryptedData, iv, code);
            principal = "";
//
//            if ("1".equals(map.get("status").toString())) {
//                principal = map.get("openId").toString();
//            }
            credentials = null;
        }
//      手机验证码登录
        else if (SPRING_SECURITY_RESTFUL_TYPE_PHONE.equals(type)) {
            principal = obtainParameter(request, SPRING_SECURITY_RESTFUL_MOBILE_KEY);
            credentials = obtainParameter(request, SPRING_SECURITY_RESTFUL_IDENTIFYCODE_KEY);
        }
        // 账号密码登陆
        else {

            principal = obtainParameter(request, SPRING_SECURITY_RESTFUL_USERNAME_KEY);
            credentials = obtainParameter(request, SPRING_SECURITY_RESTFUL_PASSWORD_KEY);
            if (type == null) {
                type = SPRING_SECURITY_RESTFUL_TYPE_DEFAULT;
            }
        }
        if (principal == null) {
            principal = "";
        }
        if (credentials == null) {
            credentials = "";
        }
        principal = principal.trim();
        authRequest = new MyAuthenticationToken(
                principal, credentials, type, mobile);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request, AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String obtainParameter(HttpServletRequest request, String parameter) {
        return request.getParameter(parameter);
    }
}
