package com.example.classdesign.service.impl;

import com.example.classdesign.entity.User;
import com.example.classdesign.mapper.FileMapper;
import com.example.classdesign.mapper.UserMapper;
import com.example.classdesign.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Resource
    FileMapper fileMapper;

    private final String filePath = "D:\\IDEAProject\\classdesign";

    @Override
    public void ChangeUsernameAndPassword(String uname, String upassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        upassword = encoder.encode(upassword);
        userMapper.ChangeUsernameAndPassword(uname, upassword);
    }

    @Override
    public void UploadFile(User user, MultipartFile file) {
        String fname = file.getOriginalFilename();
        String fpath = filePath+"\\"+fname;
        userMapper.uploadFile(fname,fpath);
        int fid = fileMapper.getLastInsertId();
        userMapper.insertUploadInfo(user.getUid(),fid,1);
    }
}
