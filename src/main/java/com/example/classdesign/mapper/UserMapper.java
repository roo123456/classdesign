package com.example.classdesign.mapper;

import com.example.classdesign.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 通过用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    User findUserByUsernameAndPassword(@Param("username")String username,@Param("password")String password);

    /**
     * 注册用户
     * @param uname
     * @param upassword
     * @param urole
     * @param nickname
     * @return
     */
    @Insert("insert into user(uname,upassword,urole,nickname) values(#{uname},#{upassword},#{urole},#{nickname})")
    int registerUser(@Param("uname")String uname,@Param("upassword")String upassword,@Param("urole")String urole,@Param("nickname")String nickname);


    /**
     * 通过用户名查找密码 用于登录
     * @param uname
     * @return
     */
    @Select("select * from user where uname = #{uname}")
    User findPasswordByUsername(String uname);

    /**
     * 改变用户名和密码 用于修改密码
     * @param uname
     * @param upassword
     */
    @Update("update user set upassword = #{upassword} where uname = #{uname}")
    void ChangeUsernameAndPassword(@Param("uname")String uname,@Param("upassword")String upassword);

    /**
     * 上传文件 添加信息到file表
     * @param fname
     * @param fpath
     */
    @Insert("insert into file(fname,fpath,fdate) values(#{fname},#{fpath},NOW())")
    void uploadFile(@Param("fname")String fname,@Param("fpath")String fpath);

    /**
     * 添加用户-文件信息到user_file表
     * @param uid
     * @param fid
     * @param status
     */
    @Insert("insert into user_file(uid,fid,status) values(#{uid},#{fid},#{status})")
    void insertUploadInfo(@Param("uid")int uid,@Param("fid")int fid,@Param("status")int status);

    /**
     * 修改user_file表中status为2 即收藏夹对应数字
     * @param uid
     * @param fid
     */
    @Update("update user_file set status = 2 where uid = #{uid} and fid = #{fid}")
    void updateStatusToFavorite(@Param("uid")int uid,@Param("fid")int fid);

    /**
     * 修改user_file表中status为1 即正常页对应数字
     * @param uid
     * @param fid
     */
    @Update("update user_file set status = 1 where uid = #{uid} and fid = #{fid}")
    void updateStatusToNormal(@Param("uid")int uid,@Param("fid")int fid);

    /**
     * 修改user_file表中status为0 即回收页对应数字
     * @param uid
     * @param fid
     */
    @Update("update user_file set status = 0 where uid = #{uid} and fid = #{fid}")
    void updateStatusToRecycle(@Param("uid")int uid,@Param("fid")int fid);

    /**
     * 查找所有用户
     * @return
     */
    @Select("select * from user")
    List<User> FindAllUsers();

    /**
     * 修改某用户的用户名和密码
     */
    @Update("update user set uname = #{uname},upassword = #{upassword} where uid = #{uid}")
    void changeUsernameAndPassword(@Param("uid")int uid,@Param("uname")String uname,@Param("upassword")String upassword);

    /**
     * 删除某用户信息
     * @param uid
     */
    @Delete("delete from user where uid = #{uid}")
    void deleteUser(int uid);

    /**
     * 删除某用户的用户-文件信息
     * @param uid
     */
    @Delete("delete from user_file where uid = #{uid}")
    void deleteUserFiles(int uid);
}
