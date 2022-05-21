package com.example.classdesign.mapper;

import com.example.classdesign.entity.File;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {
    /**
     * 获取最新的id 这里主要是用于获取自动递增的文件id
     * @return
     */
    @Select("select LAST_INSERT_ID()")
    int getLastInsertId();

    /**
     * 获取当前用户的所有正常文件
     * @param uid
     * @return
     */
    @Select("select * from file where fid in (select fid from user_file where uid = #{uid} and (status = 1 or status = 2))")
    List<File> FindFilesByUser(int uid);

    /**
     * 获取当前用户的所有收藏文件
     * @param uid
     * @return
     */
    @Select("select * from file where fid in (select fid from user_file where uid = #{uid} and status = 2)")
    List<File> FindFavoritesByUser(int uid);

    /**
     * 获取当前用户的所有垃圾文件
     * @param uid
     * @return
     */
    @Select("select * from file where fid in (select fid from user_file where uid = #{uid} and status = 0)")
    List<File> FindRecyclesByUser(int uid);

    /**
     * 删除某个文件
     * @param fid
     */
    @Delete("delete from file where fid = #{fid}")
    void DeleteFile(int fid);

    /**
     * 查找某个文件
     */
    @Select("select * from file where fid = #{fid}")
    File FindFileByFid(int fid);
}
