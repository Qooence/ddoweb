package com.qooence.code.ddoweb.config.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义 UserDetailsService
 */
@Service
public class AnyUserDetailsService implements UserDetailsService {

//    private final UserService userService;
//
//    public AnyUserDetailsService(UserService userService){
//        this.userService = userService;
//    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

//        com.zhou.model.User user = userService.getByEmail(s);
//        if (user == null){
//            throw new UsernameNotFoundException("用户不存在");
//        }

        if(StringUtils.isEmpty(s)){
            throw new UsernameNotFoundException("用户不存在");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //对应的权限添加
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        AnyUser anyUser = new AnyUser(s, "123456", authorities);
        anyUser.setId(1L);
        anyUser.setNickname("Qooence");
        return anyUser;
    }
}
