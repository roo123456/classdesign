package com.example.classdesign.service.impl;

import com.example.classdesign.entity.User;
import com.example.classdesign.mapper.FileMapper;
import com.example.classdesign.mapper.UserMapper;
import com.example.classdesign.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Resource
    FileMapper fileMapper;

    private final static String filePath = "D:\\IDEAProject\\classdesign\\files";

    @Override
    public void ChangeUsernameAndPassword(String uname, String upassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        upassword = encoder.encode(upassword);
        userMapper.ChangeUsernameAndPassword(uname, upassword);
    }

    @Override
    public void UploadFile(User user, MultipartFile file) throws IOException {
        String fname = file.getOriginalFilename();
        if ("".equals(fname)){
            return;
        }
        File newFile = new File(filePath,fname);
        //服务端保存文件
        try(InputStream is = file.getInputStream();
        OutputStream os = new FileOutputStream(newFile)){
            int len=0;
            byte[] buffer = new byte[1024];
            while ((len=is.read(buffer))!=-1){
                os.write(buffer,0,len);
                os.flush();
            }
        }
        //添加文件信息
        userMapper.uploadFile(fname,newFile.getPath());
        int fid = fileMapper.getLastInsertId();
        userMapper.insertUploadInfo(user.getUid(),fid,1);
    }

    @Override
    public void addFavorite(int fid, User user) {
        userMapper.updateStatusToFavorite(user.getUid(),fid);
    }

    @Override
    public void cancelFavorite(int fid, User user) {
        userMapper.updateStatusToNormal(user.getUid(),fid);
    }

    @Override
    public void addRecycle(int fid, User user) {
        userMapper.updateStatusToRecycle(user.getUid(),fid);
    }

    @Override
    public void cancelRecycle(int fid, User user) {
        userMapper.updateStatusToNormal(user.getUid(),fid);
    }

    @Override
    public void deleteFile(int fid, User user) {
        com.example.classdesign.entity.File file1 = fileMapper.FindFileByFid(fid);
        String fpath = file1.getFpath();
        File file = new File(fpath);
        boolean delete = file.delete();
        fileMapper.DeleteFile(fid);
    }
}
