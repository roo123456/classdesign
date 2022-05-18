package com.example.classdesign.service;

import com.example.classdesign.entity.User;

import javax.servlet.http.HttpSession;

public interface AuthService {

    /**
     * 通过用户名和密码注册用户 用于注册
     * @param uname
     * @param upassword
     */
    void registerUser(String uname,String upassword);

    /**
     * 通过session查找用户信息
     */
    User findUserBySession(HttpSession httpSession);
}
