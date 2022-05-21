package com.example.classdesign.service;

import com.example.classdesign.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    /**
     * 使用用户名修改密码 用户名与输入的不一致则直接在controller层终止
     * @param uname
     * @param upassword
     */
    void ChangeUsernameAndPassword(String uname,String upassword);

    /**
     * 用户上传文件
     */
    void UploadFile(User user, MultipartFile file) throws IOException;

    /**
     * 添加用户-文件信息到收藏夹
     * @param fid
     * @param user
     */
    void addFavorite(int fid, User user);

    /**
     * 在用户-文件中取消收藏夹信息
     * @param fid
     * @param user
     */
    void cancelFavorite(int fid, User user);

    /**
     * 添加用户-信息到回收站
     * @param fid
     * @param user
     */
    void addRecycle(int fid, User user);

    /**
     * 在用户-文件中取消回收站信息
     * @param fid
     * @param user
     */
    void cancelRecycle(int fid, User user);

    /**
     * 删除文件信息
     * @param fid
     * @param user
     */
    void deleteFile(int fid, User user);
}
