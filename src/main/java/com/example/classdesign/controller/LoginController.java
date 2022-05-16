package com.example.classdesign.controller;

import com.example.classdesign.Service.LoginService;
import com.example.classdesign.entity.User;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.logging.Logger;

@Controller
@Slf4j
public class LoginController {

    @Resource
    LoginService loginService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin(@RequestParam("username")String username,
                          @RequestParam("password")String password){
        User user = loginService.findUserByUsernameAndPassword(username,password);
        if(user == null){
            log.info("用户不存在");
            return "login";
        }
        log.info("用户"+user+"登录成功");
        return "index";
    }
}
