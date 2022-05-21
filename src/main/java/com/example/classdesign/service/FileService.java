package com.example.classdesign.service;

import com.example.classdesign.entity.File;
import com.example.classdesign.entity.User;

import java.util.List;

public interface FileService {
    /**
     * 查找当前用户的所有正常文件
     * @param user
     * @return
     */
    List<File> FindFilesByUser(User user);

    /**
     * 查找当前用户的所有收藏文件
     * @param user
     * @return
     */
    List<File> FindFavoritesByUser(User user);

    /**
     * 查找当前用户的所有垃圾文件
     * @param user
     * @return
     */
    List<File> FindRecyclesByUser(User user);

    /**
     * 查找某个文件的信息
     */
    File FindFileByFid(int fid);
}
