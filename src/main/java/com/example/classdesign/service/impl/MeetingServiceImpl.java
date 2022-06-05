package com.example.classdesign.service.impl;

import com.example.classdesign.entity.Meeting;
import com.example.classdesign.entity.MeetingFile;
import com.example.classdesign.entity.User;
import com.example.classdesign.mapper.MeetingMapper;
import com.example.classdesign.mapper.UserMapper;
import com.example.classdesign.service.MeetingService;
import com.example.classdesign.service.UserService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Resource
    MeetingMapper meetingMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public List<Meeting> getAllMeetingsByUid(int uid) {
        return meetingMapper.getAllMeetingsByUid(uid);
    }

    @Override
    public void addMeeting(int uid, String nickname, String mname) {
        String mkey = RandomStringUtils.randomAlphanumeric(8);
        meetingMapper.addMeeting(mname,uid,nickname,mkey);
    }

    @Override
    public void joinMeetingByMkey(String mkey, int uid) {
        Meeting meeting = meetingMapper.findMeetingByMkey(mkey);
        //通过会议室id查找会议室-用户对应表，查看当前用户是否已经在会议室中，如果在则直接return
        List<Integer> uids = meetingMapper.findUidsByMid(meeting.getMid());
        for(Integer id : uids){
            if(uid == id) return;
        }
        //当前用户既不是创建人，也不是会议室内用户，则可以加入会议室
        meetingMapper.joinMeeting(meeting.getMid(),uid);
    }

    @Override
    public void leaveMeeting(int mid, int uid) {
        meetingMapper.leaveMeeting(mid,uid);
    }

    @Override
    public List<MeetingFile> getAllMeetingFilesByMid(int mid) {
        return meetingMapper.getAllMeetingFilesByMid(mid);
    }

    @Override
    public void uploadInMeeting(MultipartFile meetingfile, int uid, String nickname,int mid) throws IOException {
        //拿到当前用户的用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User thisUser = userMapper.findPasswordByUsername(authentication.getName());
        String uname = thisUser.getUname();
        //拿到当前会议室的会议室名
        String mname = meetingMapper.getMnameByMid(mid);
        //拼接路径
        String filePath = "D:\\IDEAProject\\classdesign\\meetingfiles\\" + mname + "\\" + uname;//这里更改要存储的文件夹名

        String fname = meetingfile.getOriginalFilename();
        if ("".equals(fname)){
            return;
        }
        File newFile = new File(filePath);
        if(!newFile.exists()){
            newFile.mkdirs();
        }
        //服务端保存文件
        try(InputStream is = meetingfile.getInputStream();
            OutputStream os = new FileOutputStream(new File(newFile,fname))){
            int len=0;
            byte[] buffer = new byte[1024];
            while ((len=is.read(buffer))!=-1){
                os.write(buffer,0,len);
                os.flush();
            }
        }
        //添加文件信息
        meetingMapper.uploadFileInMeeting(uid,nickname,fname,newFile.getPath()+"\\"+fname);
        int fid = meetingMapper.getLastInsertId();
        meetingMapper.insertUploadInMeetingInfo(mid,fid);
    }

    @Override
    public MeetingFile FindMeetingFileByFid(int fid) {
        return meetingMapper.FindMeetingFileByFid(fid);
    }

    @Override
    public void deleteFileInMeeting(int fid) {
        MeetingFile meetingFile = meetingMapper.FindMeetingFileByFid(fid);
        //先删除文件
        String fpath = meetingFile.getFpath();
        File file = new File(fpath);
        boolean delete = file.delete();
        //再删除文件信息
        meetingMapper.deleteFileInMeeting(fid);
    }
}
