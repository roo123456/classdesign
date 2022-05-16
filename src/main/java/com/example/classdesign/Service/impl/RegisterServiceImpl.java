package com.example.classdesign.Service.impl;

import com.example.classdesign.Service.RegisterService;
import com.example.classdesign.mapper.RegisterMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    RegisterMapper registerMapper;

    @Override
    public void RegisterUser(String username, String password) {
        registerMapper.registerUser(username, password);
    }
}
