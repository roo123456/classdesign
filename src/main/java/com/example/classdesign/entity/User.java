package com.example.classdesign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    /**
     * 用户id
     * 用户名
     * 用户密码
     * 用户等级
     */
    int uid;
    String uname;
    String upassword;
    String urole;
    String nickname;
}
