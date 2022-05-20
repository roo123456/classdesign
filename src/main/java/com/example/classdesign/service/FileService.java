package com.example.classdesign.service;

import com.example.classdesign.entity.File;
import com.example.classdesign.entity.User;

import java.util.List;

public interface FileService {
    List<File> FindFilesByUser(User user);
}
