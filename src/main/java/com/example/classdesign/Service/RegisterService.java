package com.example.classdesign.Service;

public interface RegisterService {
    /**
     * 根据用户名和密码创建用户 用于注册
     * @param username
     * @param password
     */
    void RegisterUser(String username,String password);
}
