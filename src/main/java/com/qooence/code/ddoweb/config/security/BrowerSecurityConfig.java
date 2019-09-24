package com.qooence.code.ddoweb.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 安全控制中心
 */
@EnableWebSecurity //@EnableWebMvcSecurity注解  开启Spring Security的功能
public class BrowerSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    private final UserDetailsService userDetailsService;

    public BrowerSecurityConfig(AnyUserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()//authorizeRequests() 定义哪些URL需要被保护、哪些不需要被保护
            .antMatchers("/user/**","/news/**","/blog/manage/**","/blog/create/**").authenticated()
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .loginPage("/login")
            .successHandler(myAuthenticationSuccessHandler)//登陆成功处理
            .permitAll()
            .and()
            .logout()
            .permitAll()
            .and().csrf().disable();
    }
}
