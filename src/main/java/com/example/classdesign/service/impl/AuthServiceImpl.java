package com.example.classdesign.service.impl;

import com.example.classdesign.entity.User;
import com.example.classdesign.mapper.UserMapper;
import com.example.classdesign.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Resource
    UserMapper userMapper;

    @Override
    public void registerUser(String uname, String upassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(userMapper.registerUser(uname, encoder.encode(upassword), "user") <= 0){
            throw new RuntimeException("用户注册失败");
        }
    }

    @Override
    public User findUserBySession(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if(user==null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            user = userMapper.findPasswordByUsername(authentication.getName());
            httpSession.setAttribute("user",user);
        }
        return user;
    }
}
