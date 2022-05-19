package com.example.classdesign.service.impl;

import com.example.classdesign.mapper.UserMapper;
import com.example.classdesign.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public void ChangeUsernameAndPassword(String uname, String upassword,String oldUname) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        upassword = encoder.encode(upassword);
        userMapper.ChangeUsernameAndPassword(uname, upassword, oldUname);
    }
}
