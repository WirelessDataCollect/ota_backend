package com.ruili.fota.service.impl;

import com.ruili.fota.auth.filter.MyLoginAuthenticationFilter;
import com.ruili.fota.constant.AuthorityEnum;
import com.ruili.fota.meta.po.FotaRole;
import com.ruili.fota.meta.po.FotaUsers;
import com.ruili.fota.meta.vo.MenuVO;
import com.ruili.fota.service.AccountService;
import com.ruili.fota.service.AuthorityService;
import com.ruili.fota.service.BaseUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BaseUserDetailServiceImpl implements BaseUserDetailService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException {


        //**********************   依据登陆信息获取账户信息   **********************//
        FotaUsers user;
        String[] parameter;
        int index = var1.indexOf("&:@");
        if (index != -1) {
            parameter = var1.split("&:@");
        } else {
            // 如果是 refresh_token 不分割
            parameter = new String[]{MyLoginAuthenticationFilter.SPRING_SECURITY_RESTFUL_TYPE_DEFAULT, var1};
        }

        if (MyLoginAuthenticationFilter.SPRING_SECURITY_RESTFUL_TYPE_WE_CHAT.equals(parameter[0])) {
            // weixin登陆根据openId查询用户
            FotaUsers adminData = accountService.findUserByOpenId(parameter[1]);

            if (adminData == null) {
                logger.error("找不到该用户，微信号：" + parameter[1]);
                throw new UsernameNotFoundException("找不到该用户，微信号：" + parameter[1]);
            }
            user = adminData;
        } else if (MyLoginAuthenticationFilter.SPRING_SECURITY_RESTFUL_TYPE_PHONE.equals(parameter[0])) {
            // 验证码登录
            FotaUsers adminData = accountService.findUserByPhone(parameter[1]);
            if (adminData == null) {
                logger.error("找不到该用户，手机号码：" + parameter[1]);
                throw new UsernameNotFoundException("找不到该用户，手机号码：" + parameter[1]);
            }
            user = adminData;
        } else {
            // 账号密码登陆根据用户名查询用户
            FotaUsers adminData = accountService.findUserByUsername(parameter[1]);
            if (adminData == null) {
                logger.error("找不到该用户，用户名：" + parameter[1]);
                throw new UsernameNotFoundException("找不到该用户，用户名：" + parameter[1]);
            }
            user = adminData;
        }


        //**********************   根据获取到的账户信息获取权限   **********************//
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<FotaRole> roleResult = authorityService.getRoleByUser(user.getUsername());
        if (roleResult != null) {
            for (FotaRole role : roleResult) {
                //角色必须是ROLE_开头，可以在数据库中设置
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getValue());
                grantedAuthorities.add(grantedAuthority);
                //获取权限
                List<MenuVO> perResult = authorityService.getMenu(AuthorityEnum.MENU_TYPE_PC.getType(), role.getGid());
                if (perResult != null) {

                    for (MenuVO permission : perResult) {
                        //其实就是URL
                        GrantedAuthority authority = new SimpleGrantedAuthority(permission.getTitle());
                        grantedAuthorities.add(authority);
                    }
                }
            }
        }

        // 返回带有用户权限信息的User
        org.springframework.security.core.userdetails.User detailUser = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), isActive(user.getStatus()), true, true, true, grantedAuthorities);
        return detailUser;
    }

    private boolean isActive(int status) {
        return status == 1 ? true : false;
    }
}
