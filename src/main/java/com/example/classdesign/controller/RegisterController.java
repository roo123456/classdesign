package com.example.classdesign.controller;

import com.example.classdesign.Service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class RegisterController {

    @Resource
    RegisterService registerService;

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/doRegister")
    public String doRegister(@RequestParam("username")String username,
                             @RequestParam("password")String password){
        registerService.RegisterUser(username,password);
        return "login";
    }
}
