package com.example.classdesign.controller.api;

import com.example.classdesign.entity.MeetingFile;
import com.example.classdesign.entity.User;
import com.example.classdesign.service.AuthService;
import com.example.classdesign.service.FileService;
import com.example.classdesign.service.MeetingService;
import com.example.classdesign.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;

@Controller
@Slf4j
@RequestMapping("/api/user")
public class UserApiController {

    @Resource
    UserService userService;
    @Resource
    AuthService authService;
    @Resource
    FileService fileService;
    @Resource
    MeetingService meetingService;

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
    public String upload(@RequestPart("file") MultipartFile file,
                          HttpSession session) throws IOException {
        User user = authService.findUserBySession(session);
        userService.UploadFile(user,file);
        return "redirect:/page/user/index";
    }

    /**
     * 下载文件
     * @param response
     * @param fid
     * @return
     * @throws Exception
     */
    @RequestMapping("/download")
    public String downloads(HttpServletResponse response,
                            @RequestParam("fid") int fid) throws Exception{
        //查找数据库拿到文件名和路径
        com.example.classdesign.entity.File file1 = fileService.FindFileByFid(fid);
        String fname = file1.getFname();
        String fpath = file1.getFpath();

        //1、设置response 响应头
        response.reset(); //设置页面不缓存,清空buffer
        response.setCharacterEncoding("UTF-8"); //字符编码
        response.setContentType("multipart/form-data"); //二进制传输数据
        //设置响应头
        response.setHeader("Content-Disposition",
                "attachment;fileName="+ URLEncoder.encode(fname, "UTF-8"));

        File file = new File(fpath);
        //2、 读取文件--输入流
        InputStream input=new FileInputStream(file);
        //3、 写出文件--输出流
        OutputStream out = response.getOutputStream();

        byte[] buff =new byte[1024];
        int index=0;
        //4、执行 写出操作
        while((index= input.read(buff))!= -1){
            out.write(buff, 0, index);
            out.flush();
        }
        out.close();
        input.close();
        return null;
    }

    /**
     * 添加收藏夹
     * @param fid
     * @param session
     * @return
     */
    @RequestMapping("/addFavorite")
    public String addFavorite(@RequestParam("fid")int fid,
                               HttpSession session){
        User user = authService.findUserBySession(session);
        userService.addFavorite(fid,user);
        return "redirect:/page/user/index";
    }

    /**
     * 从收藏夹删除
     * @param fid
     * @param session
     * @return
     */
    @RequestMapping("/cancelFavorite")
    public String cancelFavorite(@RequestParam("fid")int fid,
                                 HttpSession session){
        User user = authService.findUserBySession(session);
        userService.cancelFavorite(fid,user);
        return "redirect:/page/user/favorite";
    }

    /**
     * 添加回收站
     * @param fid
     * @param session
     * @return
     */
    @RequestMapping("/addRecycle")
    public String addRecycle(@RequestParam("fid")int fid,
                             HttpSession session){
        User user = authService.findUserBySession(session);
        userService.addRecycle(fid,user);
        return "redirect:/page/user/index";
    }

    /**
     * 从回收站恢复
     * @param fid
     * @param session
     * @return
     */
    @RequestMapping("/cancelRecycle")
    public String cancelRecycle(@RequestParam("fid")int fid,
                                HttpSession session){
        User user = authService.findUserBySession(session);
        userService.cancelRecycle(fid,user);
        return "redirect:/page/user/recycle";
    }

    /**
     * 从回收站删除文件
     * @param fid
     * @param session
     * @return
     */
    @RequestMapping("/deleteFile")
    public String deleteFile(@RequestParam("fid")int fid,
                             HttpSession session){
        User user = authService.findUserBySession(session);
        userService.deleteFile(fid,user);
        return "redirect:/page/user/recycle";
    }

    /**
     * 创建会议室
     * @param mname
     * @param httpSession
     * @return
     */
    @RequestMapping("/addMeeting")
    public String addMeeting(@RequestParam("meetingname")String mname,
                             HttpSession httpSession){
        User user = authService.findUserBySession(httpSession);
        meetingService.addMeeting(user.getUid(),user.getNickname(),mname);
        return "redirect:/page/user/meeting";
    }

    /**
     * 加入会议室
     * @param mkey
     * @param httpSession
     * @return
     */
    @RequestMapping("/joinMeeting")
    public String joinMeeting(@RequestParam("meetingkey")String mkey,
                               HttpSession httpSession){
        User user = authService.findUserBySession(httpSession);
        meetingService.joinMeetingByMkey(mkey,user.getUid());
        return "redirect:/page/user/meeting";
    }

    /**
     * 离开会议室
     * @param mid
     * @param httpSession
     * @return
     */
    @RequestMapping("/leaveMeeting")
    public String leaveMeeting(@RequestParam("mid")int mid,
                               HttpSession httpSession){
        User user = authService.findUserBySession(httpSession);
        meetingService.leaveMeeting(mid,user.getUid());
        return "redirect:/page/user/meeting";
    }

    /**
     * 上传文件到会议室
     * @param meetingfile
     * @param mid
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/uploadInMeeting")
    public String uploadInMeeting(@RequestPart("meetingfile") MultipartFile meetingfile,
                                  @RequestParam("mid")int mid,
                                  HttpSession session) throws IOException {
        User user = authService.findUserBySession(session);
        meetingService.uploadInMeeting(meetingfile,user.getUid(),user.getNickname(),mid);
        return "redirect:/page/user/meetingFiles?mid=" + mid;
    }

    /**
     * 从会议室下载文件
     * @param response
     * @param fid
     * @return
     * @throws Exception
     */
    @RequestMapping("/downloadInMeeting")
    public String downloadInMeeting(HttpServletResponse response,
                            @RequestParam("fid") int fid) throws Exception{
        //查找数据库拿到文件名和路径
        MeetingFile file1 = meetingService.FindMeetingFileByFid(fid);
        String fname = file1.getFname();
        String fpath = file1.getFpath();

        //1、设置response 响应头
        response.reset(); //设置页面不缓存,清空buffer
        response.setCharacterEncoding("UTF-8"); //字符编码
        response.setContentType("multipart/form-data"); //二进制传输数据
        //设置响应头
        response.setHeader("Content-Disposition",
                "attachment;fileName="+ URLEncoder.encode(fname, "UTF-8"));

        File file = new File(fpath);
        //2、 读取文件--输入流
        InputStream input=new FileInputStream(file);
        //3、 写出文件--输出流
        OutputStream out = response.getOutputStream();

        byte[] buff =new byte[1024];
        int index=0;
        //4、执行 写出操作
        while((index= input.read(buff))!= -1){
            out.write(buff, 0, index);
            out.flush();
        }
        out.close();
        input.close();
        return null;
    }

    /**
     * 从会议室中删除文件
     * @param fid
     * @param mid
     * @return
     */
    @RequestMapping("/deleteFileInMeeting")
    public String deleteFileInMeeting(@RequestParam("fid")int fid,
                                      @RequestParam("mid")int mid){
        meetingService.deleteFileInMeeting(fid);
        return "redirect:/page/user/meetingFiles?mid=" + mid;
    }
}
