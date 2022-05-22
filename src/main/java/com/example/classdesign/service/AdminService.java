package com.example.classdesign.service;

import com.example.classdesign.entity.File;
import com.example.classdesign.entity.User;
import com.example.classdesign.entity.UserFiles;

import java.util.List;

public interface AdminService {
    /**
     * 查找所有文件信息
     * @return
     */
    List<UserFiles> FindAllFiles();

    /**
     * 查找所有用户信息
     */
    List<User> FindAllUsers();

    /**
     * 修改某个用户的用户名和密码
     */
    void ChangeUsernameAndPassword(int uid,String uname,String upassword);

    /**
     * 查找某个用户上传的所有文件id
     * @param uid
     */
    List<Integer> FindOneUserFids(int uid);
}
