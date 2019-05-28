package com.ruili.fota.controller;

import com.ruili.fota.common.RequestHelper;
import com.ruili.fota.meta.po.FotaUsers;
import com.ruili.fota.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/test")
public class BaseController {

    @Autowired
    private AccountService accountService;

    @Autowired
    protected HttpServletRequest request;

    public RequestHelper getRequestHelper(){
        return new RequestHelper(this.request);
    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 获取当前用户
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
