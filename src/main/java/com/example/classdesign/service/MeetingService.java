package com.example.classdesign.service;

import com.example.classdesign.entity.Meeting;
import com.example.classdesign.entity.MeetingFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MeetingService {
    /**
     * 获取某用户加入的所有会议室
     * @param uid
     * @return
     */
    List<Meeting> getAllMeetingsByUid(int uid);

    /**
     * 某用户新建一个会议室
     * @param uid
     * @param nickname
     * @param mname
     */
    void addMeeting(int uid, String nickname, String mname);

    /**
     * 某用户加入一个会议室
     * @param mkey
     * @param uid
     */
    void joinMeetingByMkey(String mkey, int uid);

    /**
     * 某用户退出一个会议室
     * @param mid
     * @param uid
     */
    void leaveMeeting(int mid, int uid);

    /**
     * 获取某个会议室内的所有文件信息
     * @param mid
     * @return
     */
    List<MeetingFile> getAllMeetingFilesByMid(int mid);

    /**
     * 在会议室中上传文件
     * @param meetingfile
     * @param uid
     * @param nickname
     */
    void uploadInMeeting(MultipartFile meetingfile, int uid, String nickname,int mid) throws IOException;
}
