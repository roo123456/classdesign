package com.example.classdesign.controller.page;

import com.example.classdesign.entity.User;
import com.example.classdesign.service.AuthService;
import com.example.classdesign.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping("/page/user")
public class UserPageController {

    @Resource
    AuthService authService;
    @Resource
    FileService fileService;

    /**
     * 打开user主页
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpSession httpSession, Model model){
        User user = authService.findUserBySession(httpSession);
        model.addAttribute("user",user);
        model.addAttribute("files",fileService.FindFilesByUser(user));
        return "/user/index";
    }

    /**
     * 打开修改密码页
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping("/profile")
    public String profile(HttpSession httpSession, Model model){
        model.addAttribute("user",authService.findUserBySession(httpSession));
        return "/user/profile";
    }

    /**
     * 打开收藏夹页
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping("/favorites")
    public String favorites(HttpSession httpSession, Model model){
        model.addAttribute("user",authService.findUserBySession(httpSession));
        return "/user/favorites";
    }

    /**
     * 打开回收站页
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping("/recycle")
    public String recycle(HttpSession httpSession, Model model){
        model.addAttribute("user",authService.findUserBySession(httpSession));
        return "/user/recycle";
    }
}
