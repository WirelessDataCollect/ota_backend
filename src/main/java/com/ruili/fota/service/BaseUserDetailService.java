package com.ruili.fota.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface BaseUserDetailService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;
}
