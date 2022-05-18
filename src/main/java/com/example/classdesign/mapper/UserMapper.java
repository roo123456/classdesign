package com.example.classdesign.mapper;

import com.example.classdesign.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username} and password = #{password}")
    User findUserByUsernameAndPassword(@Param("username")String username,@Param("password")String password);

    @Insert("insert into user(uname,upassword,urole) values(#{uname},#{upassword},#{urole})")
    int registerUser(@Param("uname")String uname,@Param("upassword")String upassword,@Param("urole")String urole);

    @Select("select * from user where uname = #{uname}")
    User findPasswordByUsername(String uname);
}
