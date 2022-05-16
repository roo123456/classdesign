package com.example.classdesign.Service;

import com.example.classdesign.entity.User;

public interface LoginService {
    /**
     * 使用用户名和密码查找用户 用于登录
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(String username, String password);
}
