package com.ruili.fota.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by Yin on 2019/2/11
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
//                        "/**",
                        //系统通用
                        "/sys/**",
                        //接口文档接口
                        "/swagger-ui.html/**",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/noauth/**",
                        "/v2/**",
                        "/firmware/upload"
                ).permitAll();
        http.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated();
    }
}
