package com.qooence.code.ddoweb.module.system.controller;

import com.qooence.code.order.api.IOrderService;
import com.qooence.code.order.dto.DoOrderRequest;
import com.qooence.code.order.dto.DoOrderResponse;
import com.qooence.code.usercenter.api.IUserService;
import com.qooence.code.usercenter.dto.User;
import com.qooence.code.usercenter.dto.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    @GetMapping("sys/login")
    public void login(){
        DoOrderRequest request = new DoOrderRequest();
        request.setName("Qooence");
        UserLoginRequest request1 = new UserLoginRequest();
        userService.login(request1);
        DoOrderResponse response = orderService.doOrder(request);
        System.out.println(response);
    }


    @RequestMapping("/{id}")
    public String  getUser(@PathVariable int id, Model model) {
        User dto = new User();
        dto.setId((long) id);
        dto.setUsername("pepstack-"+id);
        dto.setAddress("Shanghai, China");
        dto.setAge(20 + id);
        model.addAttribute("user", dto);
        return "/user/detail";
    }


    @RequestMapping("/list")
    public String  listUser(Model model) {
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < 9; i++) {
            User dto = new User();
            dto.setId((long) i);
            dto.setUsername("pepstack-" + i);
            dto.setAddress("Shanghai, China");
            dto.setAge(20 + i);
            userList.add(dto);
        }
        model.addAttribute("users", userList);
        return "/user/list";
    }

}
