package com.example.classdesign.service;

import com.example.classdesign.entity.User;
import org.springframework.web.multipart.MultipartFile;

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
    void UploadFile(User user, MultipartFile file);
}
