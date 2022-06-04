package com.example.classdesign.controller.page;

import com.example.classdesign.entity.User;
import com.example.classdesign.service.AuthService;
import com.example.classdesign.service.FileService;
import com.example.classdesign.service.MeetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Resource
    MeetingService meetingService;

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
    @RequestMapping("/favorite")
    public String favorites(HttpSession httpSession, Model model){
        User user = authService.findUserBySession(httpSession);
        model.addAttribute("user",user);
        model.addAttribute("favorites",fileService.FindFavoritesByUser(user));
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
        User user = authService.findUserBySession(httpSession);
        model.addAttribute("user",user);
        model.addAttribute("recycles",fileService.FindRecyclesByUser(user));
        return "/user/recycle";
    }

    /**
     * 打开会议室页
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping("/meeting")
    public String meeting(HttpSession httpSession,Model model){
        User user = authService.findUserBySession(httpSession);
        model.addAttribute("user",user);
        model.addAttribute("meetings",meetingService.getAllMeetingsByUid(user.getUid()));
        return "/user/meeting";
    }

    /**
     * 新建会议室
     * @param httpSession
     * @param model
     * @return
     */
    @RequestMapping("/addMeeting")
    public String addMeeting(HttpSession httpSession, Model model){
        User user = authService.findUserBySession(httpSession);
        model.addAttribute("user",user);
        return "/user/add-meeting";
    }

    @RequestMapping("/enterMeeting")
    public String enterMeeting(HttpSession httpSession,Model model){
        User user = authService.findUserBySession(httpSession);
        model.addAttribute("user",user);
        return "/user/enter-meeting";
    }
}
