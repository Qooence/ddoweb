package com.qooence.code.ddoweb.module.system.controller;

import com.qooence.code.usercenter.api.IUserService;
import com.qooence.code.usercenter.dto.User;
import com.qooence.code.usercenter.dto.UserLoginRequest;
import com.qooence.code.usercenter.dto.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("/index");
    }

    /**
     * 自定义登录页面
     * @param error 错误信息显示标识
     * @return
     *
     */
    @GetMapping("/login")
    public ModelAndView login(String error){
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }

    @RequestMapping("/dologin")
    public Object dologin(User user){
        UserLoginRequest request = new UserLoginRequest();
        request.setName(user.getUsername());
        request.setPassword(user.getPassword());
        UserLoginResponse response = userService.login(request);
        System.out.println("fdsf");
        return response;
    }

}
