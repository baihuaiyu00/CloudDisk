package org.log.mapper;

import org.log.entity.*;

import java.util.List;

/**
 * Created by Admin on 2016/5/25.
 */
public interface FileOperationMapper extends SqlMapper {

    void delFile(FileDelBean fileDelBean);

    List<FileListBean> findFileByUsername(UserLoginBean userLoginBean);

    String findPathByUsername(FileShareBean fileShareBean);

    void fileUpload(FileUploadBean fileUploadBean);

    void addShareFile(FileShareBean fileShareBean);

    FileShareBean findInfoByCode(String md5code);

}
