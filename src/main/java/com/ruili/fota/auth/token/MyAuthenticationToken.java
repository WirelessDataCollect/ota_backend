package com.ruili.fota.auth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by Yin on 2019/2/11
 * 自定义AbstractAuthenticationToken，
 * 新增属性 type: 登陆类型、mobile：移动端设备id
 */
public class MyAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 110L;
    private final Object principal;
    private Object credentials;
    private String type;
    private String mobile;

    public MyAuthenticationToken(Object principal, Object credentials, String type, String mobile) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.type = type;
        this.mobile = mobile;
        this.setAuthenticated(false);
    }

    public MyAuthenticationToken(Object principal, Object credentials, String type, String mobile, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.type = type;
        this.mobile = mobile;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public String getType() {
        return this.type;
    }

    public String getMobile() {
        return this.mobile;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
