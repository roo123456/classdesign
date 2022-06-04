package com.example.classdesign.service;

import com.example.classdesign.entity.Meeting;

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
    void enterMeetingByMkey(String mkey, int uid);

    /**
     * 某用户退出一个会议室
     * @param mid
     * @param uid
     */
    void leaveMeeting(int mid, int uid);
}
