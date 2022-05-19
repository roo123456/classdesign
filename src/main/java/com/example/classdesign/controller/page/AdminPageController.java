package com.example.classdesign.controller.page;

import com.example.classdesign.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/admin")
public class AdminPageController {

    @Resource
    AuthService authService;

    @RequestMapping("/index")
    public String index(HttpSession httpSession, Model model){
        model.addAttribute("user",authService.findUserBySession(httpSession));
        return "/admin/index";
    }
}
