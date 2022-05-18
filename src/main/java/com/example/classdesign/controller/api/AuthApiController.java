package com.example.classdesign.controller.api;

import com.example.classdesign.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api/auth")
@Slf4j
public class AuthApiController {

    @Resource
    AuthService authService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String doRegister(@RequestParam("username")String username,
                             @RequestParam("password")String password){
        authService.registerUser(username,password);
        return "redirect:/login";
    }
}
