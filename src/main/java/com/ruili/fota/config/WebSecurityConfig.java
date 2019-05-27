package com.ruili.fota.config;

import com.ruili.fota.auth.filter.MyLoginAuthenticationFilter;
import com.ruili.fota.auth.provider.MyAuthenticationProvider;
import com.ruili.fota.service.BaseUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BaseUserDetailService baseUserDetailService;

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(myAuthenticationProvider());
    }

    //"/login"和"/oauth/token"分别是登陆和登陆的路由
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/favor.ioc", "/login", "/oauth/token");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterAt(getMyLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login").permitAll()
                .and()
                .csrf().disable();
    }

    /**
     * 自定义登陆过滤器
     *
     * @return
     */
    @Bean
    public MyLoginAuthenticationFilter getMyLoginAuthenticationFilter() {
        MyLoginAuthenticationFilter filter = new MyLoginAuthenticationFilter();
        try {
            filter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        filter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        return filter;
    }

    /**
     * 自定义密码验证
     *
     * @return
     */
    @Bean
    public MyAuthenticationProvider myAuthenticationProvider() {
        MyAuthenticationProvider provider = new MyAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(baseUserDetailService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
//        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

}
