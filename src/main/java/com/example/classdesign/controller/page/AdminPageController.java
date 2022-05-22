package com.example.classdesign.controller.page;

import com.example.classdesign.entity.User;
import com.example.classdesign.service.AdminService;
import com.example.classdesign.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/admin")
public class AdminPageController {

    @Resource
    AuthService authService;
    @Resource
    AdminService adminService;

    /**
     * 打开admin主页
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpSession httpSession, Model model){
        User user = authService.findUserBySession(httpSession);
        model.addAttribute("user",user);
        model.addAttribute("userFiles",adminService.FindAllFiles());
        model.addAttribute("users",adminService.FindAllUsers());
        return "/admin/index";
    }

    /**
     * 打开用户管理页面
     */
    @RequestMapping("/users")
    public String users(HttpSession httpSession,Model model){
        User user = authService.findUserBySession(httpSession);
        model.addAttribute("user",user);
        model.addAttribute("userFiles",adminService.FindAllFiles());
        model.addAttribute("users",adminService.FindAllUsers());
        return "/admin/users";
    }

    /**
     * 修改用户名和密码页面
     */
    @RequestMapping("/change")
    public String change(HttpSession httpSession,Model model,
                         @RequestParam("uid")int uid){
        model.addAttribute("user",authService.findUserBySession(httpSession));
        //保存前端传进来的要修改的uid到session，这样页面转换了还可以从session中拿到要修改的uid
        httpSession.setAttribute("uid",uid);
        return "/admin/profile";
    }
}
