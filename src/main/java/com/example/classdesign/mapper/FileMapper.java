package com.example.classdesign.mapper;

import com.example.classdesign.entity.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("select LAST_INSERT_ID()")
    int getLastInsertId();

    @Select("select * from file where fid in (select fid from user_file where uid = #{uid});")
    List<File> FindFilesByUser(int uid);
}
