package com.example.classdesign.service.impl;

import com.example.classdesign.entity.File;
import com.example.classdesign.entity.User;
import com.example.classdesign.entity.UserFiles;
import com.example.classdesign.mapper.FileMapper;
import com.example.classdesign.mapper.UserMapper;
import com.example.classdesign.service.AdminService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    FileMapper fileMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public List<UserFiles> FindAllFiles() {
        return fileMapper.FindAllFiles();
    }

    @Override
    public List<User> FindAllUsers() {
        return userMapper.FindAllUsers();
    }

    @Override
    public void ChangeUsernameAndPassword(int uid, String uname, String upassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        upassword = encoder.encode(upassword);
        userMapper.changeUsernameAndPassword(uid, uname, upassword);
    }

    @Override
    public List<Integer> FindOneUserFids(int uid) {
        return fileMapper.FindOneUserFids(uid);
    }
}
