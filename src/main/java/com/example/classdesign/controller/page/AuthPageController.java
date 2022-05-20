package com.example.classdesign.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/auth")
public class AuthPageController {

    /**
     * 打开注册页
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 打开登录页
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
