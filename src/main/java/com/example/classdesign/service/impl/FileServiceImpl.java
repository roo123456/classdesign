package com.example.classdesign.service.impl;

import com.example.classdesign.entity.File;
import com.example.classdesign.entity.User;
import com.example.classdesign.mapper.FileMapper;
import com.example.classdesign.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    FileMapper fileMapper;

    @Override
    public List<File> FindFilesByUser(User user) {
        return fileMapper.FindFilesByUser(user.getUid());
    }
}
