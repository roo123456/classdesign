package com.example.classdesign.service.impl;

import com.example.classdesign.entity.User;
import com.example.classdesign.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findPasswordByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("登录失败，用户名或密码错误！");
        return org.springframework.security.core.userdetails.User   //这里需要返回UserDetails，SpringSecurity会根据给定的信息进行比对
                .withUsername(user.getUname())
                .password(user.getUpassword())   //直接从数据库取的密码
                .roles(user.getUrole())   //用户角色
                .build();
    }
}
