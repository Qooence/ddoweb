package com.qooence.code.ddoweb.module.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class LoginController {


    /**
     * 自定义登录页面
     * @param error 错误信息显示标识
     * @return
     *
     */
    @GetMapping("login")
    public ModelAndView login(String error){
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }

}
