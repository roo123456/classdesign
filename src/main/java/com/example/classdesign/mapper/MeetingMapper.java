package com.example.classdesign.mapper;

import com.example.classdesign.entity.Meeting;
import com.example.classdesign.entity.MeetingFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MeetingMapper {

    /**
     * 获取某用户加入的所有会议室
     * @param uid
     * @return
     */
    @Select("select * from meeting where mid in (select mid from meeting_user where uid = #{uid})")
    List<Meeting> getAllMeetingsByUid(int uid);

    /**
     * 新建会议室信息
     * @param mname
     * @param uid
     * @param nickname
     * @param mkey
     */
    @Insert("insert into meeting(mname,uid,nickname,mdate,mkey) values(#{mname},#{uid},#{nickname},NOW(),#{mkey})")
    void addMeeting(@Param("mname")String mname,@Param("uid")int uid,@Param("nickname")String nickname,@Param("mkey")String mkey);

    /**
     * 通过会议室号查找会议室
     * @param mkey
     * @return
     */
    @Select("select * from meeting where mkey = #{mkey}")
    Meeting findMeetingByMkey(String mkey);

    /**
     * 通过会议室号查找当前会议室内所有用户id
     * @param mid
     * @return
     */
    @Select("select uid from meeting_user where mid = #{mid}")
    List<Integer> findUidsByMid(int mid);

    /**
     * 插入会议室id和用户id进会议室-用户对应表
     * @param mid
     * @param uid
     */
    @Insert("insert into meeting_user(mid,uid) values(#{mid},#{uid})")
    void joinMeeting(@Param("mid") int mid,@Param("uid") int uid);

    /**
     * 通过会议室id和用户id删除会议室-用户对应表中的信息
     * @param mid
     * @param uid
     */
    @Delete("delete from meeting_user where mid = #{mid} and uid = #{uid}")
    void leaveMeeting(@Param("mid") int mid,@Param("uid") int uid);

    /**
     * 通过会议室id查找该会议室内所有文件信息
     * @param mid
     * @return
     */
    @Select("select * from meetingfile where fid in (select fid from meeting_meetingfile where mid = #{mid})")
    List<MeetingFile> getAllMeetingFilesByMid(int mid);

    @Select("select mname from meeting where mid = #{mid}")
    String getMnameByMid(int mid);

    @Insert("insert into meetingfile(uid,nickname,fname,fpath,fdate) values(#{uid},#{nickname},#{fname},#{fpath},NOW())")
    void uploadFileInMeeting(@Param("uid") int uid,@Param("nickname") String nickname,
                             @Param("fname") String fname,@Param("fpath") String fpath);

    @Select("select LAST_INSERT_ID()")
    int getLastInsertId();

    @Insert("insert into meeting_meetingfile(mid,fid) values(#{mid},#{fid})")
    void insertUploadInMeetingInfo(@Param("mid") int mid,@Param("fid") int fid);

    @Select("select * from meetingfile where fid = #{fid}")
    MeetingFile FindMeetingFileByFid(int fid);

    @Delete("delete from meetingfile where fid = #{fid}")
    void deleteFileInMeeting(int fid);
}
