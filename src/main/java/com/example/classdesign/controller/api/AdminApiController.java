package com.example.classdesign.controller.api;

import com.example.classdesign.entity.User;
import com.example.classdesign.service.AdminService;
import com.example.classdesign.service.AuthService;
import com.example.classdesign.service.FileService;
import com.example.classdesign.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/api/admin")
public class AdminApiController {

    @Resource
    FileService fileService;
    @Resource
    AuthService authService;
    @Resource
    UserService userService;
    @Resource
    AdminService adminService;

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

    @RequestMapping("/deleteFile")
    public String deleteFile(@RequestParam("fid")int fid,
                             HttpSession session){
        //这个user没啥用 删除的时候也不需要user身份 只是懒得改了
        User user = authService.findUserBySession(session);
        userService.deleteFile(fid,user);
        return "redirect:/page/admin/index";
    }

    @RequestMapping("/change")
    public String change(@RequestParam("username")String uname,
                         @RequestParam("password")String upassword,
                         HttpSession session){
        int uid = (int) session.getAttribute("uid");
        adminService.ChangeUsernameAndPassword(uid,uname,upassword);
        return "redirect:/page/admin/users";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("uid")int uid,
                             HttpSession session){
        //删用户上传的所有文件
        //先查找该用户的所有上传文件的id，再依次调用deleteFile删除
        List<Integer> fids = adminService.FindOneUserFids(uid);
        for(Integer fid : fids){
            userService.deleteFile(fid,authService.findUserBySession(session));
        }
        //删用户信息和用户-文件信息
        userService.deleteUser(uid);
        userService.deleteUserFiles(uid);
        return "redirect:/page/admin/users";
    }
}
