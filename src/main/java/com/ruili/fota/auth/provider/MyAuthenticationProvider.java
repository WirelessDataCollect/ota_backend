package com.ruili.fota.auth.provider;

import com.ruili.fota.auth.filter.MyLoginAuthenticationFilter;
import com.ruili.fota.auth.token.MyAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/**
 * Created by Yin on 2019/2/11
 * 自定义密码验证
 * 实现自定义的 MyAbstractUserDetailsAuthenticationProvider 抽象类
 * 根据登陆的类型 执行不同的校验
 */
@Component
public class MyAuthenticationProvider extends MyAbstractUserDetailsAuthenticationProvider {

    private PasswordEncoder passwordEncoder;
    private String userNotFoundEncodedPassword;
    private SaltSource saltSource;
    private UserDetailsService userDetailsService;
    private static MyAuthenticationProvider myAuthenticationProvider;

//    @Resource
//    private SmsService smsService;

    @PostConstruct
    public void init() {
        myAuthenticationProvider = this;
//        myAuthenticationProvider.smsService = this.smsService;
    }


    public MyAuthenticationProvider() {
        this.setPasswordEncoder((PasswordEncoder) (new PlaintextPasswordEncoder()));
    }

    /**
     * 自定义验证
     *
     * @param userDetails
     * @param authentication
     * @throws AuthenticationException
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, MyAuthenticationToken authentication) throws AuthenticationException {
        Object salt = null;
        if (this.saltSource != null) {
            salt = this.saltSource.getSalt(userDetails);
        }

        if (authentication.getCredentials() == null) {
            this.logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("MyAbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            String presentedUsername = authentication.getPrincipal().toString();
            String presentedPassword = authentication.getCredentials().toString();

            // 验证开始
            if (MyLoginAuthenticationFilter.SPRING_SECURITY_RESTFUL_TYPE_WeChat.equals(authentication.getType())) {
                // 微信只需要根据 SPRING_SECURITY_RESTFUL_TYPE_WeChat 查询到微信号即可，所以此处无需验证
            } else if (MyLoginAuthenticationFilter.SPRING_SECURITY_RESTFUL_TYPE_PHONE.equals(authentication.getType())) {
                // 手机短信登录
                // 验证码验证，调用公共服务查询 key 为authentication.getPrincipal()的value， 并判断其与验证码是否匹配
//                int res = smsService.checkIsCorrectCode(presentedUsername, presentedPassword).getStatus();
//                if (res != 1) {
//                    this.logger.debug("Authentication failed: verifyCode does not match stored value");
//                    throw new BadCredentialsException(this.messages.getMessage("MyAbstractUserDetailsAuthenticationProvider.badCredentials", "Bad verifyCode"));
//                }
            } else {
                // 用户名密码验证
                if (!this.passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
                    this.logger.debug("Authentication failed: password does not match stored value");
                    throw new BadCredentialsException(this.messages.getMessage("MyAbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
                }
            }
        }
    }

    @Override
    protected void doAfterPropertiesSet() throws Exception {
        Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
    }

    @Override
    protected final UserDetails retrieveUser(String username, MyAuthenticationToken authentication) throws AuthenticationException {
        UserDetails loadedUser;
        try {
            // 调用loadUserByUsername时加入type前缀
            loadedUser = this.getUserDetailsService().loadUserByUsername(authentication.getType() + "&:@" + username);
        } catch (UsernameNotFoundException var6) {
            if (authentication.getCredentials() != null) {
                String presentedPassword = authentication.getCredentials().toString();
                this.passwordEncoder.isPasswordValid(this.userNotFoundEncodedPassword, presentedPassword, null);
            }
            throw var6;
        } catch (Exception var7) {
            throw new InternalAuthenticationServiceException(var7.getMessage(), var7);
        }

        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        } else {
            return loadedUser;
        }
    }

    public void setPasswordEncoder(Object passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        if (passwordEncoder instanceof PasswordEncoder) {
            this.setPasswordEncoder((PasswordEncoder) passwordEncoder);
        } else if (passwordEncoder instanceof org.springframework.security.crypto.password.PasswordEncoder) {
            final org.springframework.security.crypto.password.PasswordEncoder delegate = (org.springframework.security.crypto.password.PasswordEncoder) passwordEncoder;
            this.setPasswordEncoder(new PasswordEncoder() {
                @Override
                public String encodePassword(String rawPass, Object salt) {
                    this.checkSalt(salt);
                    return delegate.encode(rawPass);
                }

                @Override
                public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
                    this.checkSalt(salt);
                    return delegate.matches(rawPass, encPass);
                }

                private void checkSalt(Object salt) {
                    Assert.isNull(salt, "Salt value must be null when used with crypto module PasswordEncoder");
                }
            });
        } else {
            throw new IllegalArgumentException("passwordEncoder must be a PasswordEncoder instance");
        }
    }

    private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.userNotFoundEncodedPassword = passwordEncoder.encodePassword("userNotFoundPassword", (Object) null);
        this.passwordEncoder = passwordEncoder;
    }

    protected PasswordEncoder getPasswordEncoder() {
        return this.passwordEncoder;
    }

    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }

    protected SaltSource getSaltSource() {
        return this.saltSource;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    protected UserDetailsService getUserDetailsService() {
        return this.userDetailsService;
    }
}
