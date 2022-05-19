package com.example.classdesign.controller.api;

import com.example.classdesign.entity.User;
import com.example.classdesign.service.AuthService;
import com.example.classdesign.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/api/user")
public class UserApiController {

    @Resource
    UserService userService;
    @Resource
    AuthService authService;

    @RequestMapping("/change")
    public String change(@RequestParam("username")String uname,
                         @RequestParam("password")String upassword,
                         HttpSession session){
        User user = authService.findUserBySession(session);
        //如果输入修改的用户名和当前登录的用户名不一致 不能修改
        if(!user.getUname().equals(uname)) {
            return "redirect:/api/auth/logout";
        }
        userService.ChangeUsernameAndPassword(uname,upassword,user.getUname());
        return "redirect:/api/auth/logout";
    }
}
