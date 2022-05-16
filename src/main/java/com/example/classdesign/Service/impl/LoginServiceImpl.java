package com.example.classdesign.Service.impl;

import com.example.classdesign.Service.LoginService;
import com.example.classdesign.entity.User;
import com.example.classdesign.mapper.LoginMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    LoginMapper loginMapper;

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return loginMapper.findUserByUsernameAndPassword(username, password);
    }
}
