package com.ruili.fota.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface BaseUserDetailService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(@NotBlank String userName) throws UsernameNotFoundException;
}
