package com.example.classdesign.controller.api;

import com.example.classdesign.entity.User;
import com.example.classdesign.service.AuthService;
import com.example.classdesign.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Slf4j
@RequestMapping("/api/user")
public class UserApiController {

    @Resource
    UserService userService;
    @Resource
    AuthService authService;

    /**
     * 修改密码
     * @param uname
     * @param upassword
     * @param session
     * @return
     */
    @RequestMapping("/change")
    public String change(@RequestParam("username")String uname,
                         @RequestParam("password")String upassword,
                         HttpSession session){
        User user = authService.findUserBySession(session);
        //如果输入修改的用户名和当前登录的用户名不一致 不能修改
        if(!user.getUname().equals(uname)) {
            return "redirect:/api/auth/logout";
        }
        userService.ChangeUsernameAndPassword(uname,upassword);
        return "redirect:/api/auth/logout";
    }

    /**
     * 上传文件
     * @param file
     * @param session
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String getFile(@RequestPart("file") MultipartFile file,
                          HttpSession session) throws IOException {
        User user = authService.findUserBySession(session);
        userService.UploadFile(user,file);
        return "redirect:/page/user/index";
    }
}
