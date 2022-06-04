package com.example.classdesign.service.impl;

import com.example.classdesign.entity.Meeting;
import com.example.classdesign.mapper.MeetingMapper;
import com.example.classdesign.service.MeetingService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Resource
    MeetingMapper meetingMapper;

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
    public void enterMeetingByMkey(String mkey, int uid) {
        Meeting meeting = meetingMapper.findMeetingByMkey(mkey);
        //如果当前用户是该会议室创建人，则直接return
        if(uid == meeting.getUid()) return;
        //通过会议室id查找会议室-用户对应表，查看当前用户是否已经在会议室中，如果在则直接return
        List<Integer> uids = meetingMapper.findUidsByMid(meeting.getMid());
        for(Integer id : uids){
            if(uid == id) return;
        }
        //当前用户既不是创建人，也不是会议室内用户，则可以加入会议室
        meetingMapper.enterMeeting(meeting.getMid(),uid);
    }

    @Override
    public void leaveMeeting(int mid, int uid) {
        meetingMapper.leaveMeeting(mid,uid);
    }
}
