package com.example.classdesign.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegisterMapper {

    @Insert("insert into user(username,password) values(#{username},#{password})")
    void registerUser(@Param("username")String username,@Param("password")String password);

}
