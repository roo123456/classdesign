package com.example.classdesign.controller.page;

import com.example.classdesign.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/page/user")
public class UserPageController {

    @Resource
    AuthService authService;

    @RequestMapping("/index")
    public String index(HttpSession httpSession, Model model){
        model.addAttribute("user",authService.findUserBySession(httpSession));
        return "/user/index";
    }
}
