package com.ruili.fota.controller;

import com.ruili.fota.common.RequestHelper;
import com.ruili.fota.constant.AuthorityEnum;
import com.ruili.fota.constant.result.BaseResp;
import com.ruili.fota.meta.po.FotaUsers;
import com.ruili.fota.service.AccountService;
import com.ruili.fota.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class BaseController {

    @Autowired
    private AccountService accountService;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    private AuthorityService authorityService;

    public RequestHelper getRequestHelper() {
        return new RequestHelper(this.request);
    }

    /**
     * 通过controller前面的main路由判断用户是否有权限访问当前路径
     * //todo 可以使用切面编程优雅实现
     *
     * @param urlPrefix
     * @return
     */
    public boolean checkPermission(String urlPrefix) {
        List<Map> menuPermission = authorityService.getMenuByUser(AuthorityEnum.MENU_TYPE_PC.getType(),
            this.findCurrentUser().getGid());
        for (Map<String, String> element : menuPermission) {
            if (element.get("code").contains(urlPrefix)) {
                return true;
            }
        }
        return false;
    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
            .getRequest();
        return request;
    }

    private String getIpAddr() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
            .getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if ("127.0.0.1".equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public FotaUsers findCurrentUser() {
        //获得当前登陆用户的username
        String username = request.getRemoteUser();
        if (!StringUtils.isEmpty(username)) {
            FotaUsers currentUser = accountService.findUserByUsername(username);
            return currentUser;
        }
        return null;
    }

}
