package org.log.service;

import org.log.entity.FileListBean;
import org.log.entity.UserLoginBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 2016/4/28.
 */

@Service
public interface FileListService {
    List<FileListBean> showFiles(UserLoginBean userLoginBean);

    List<FileListBean> chooseType(List<FileListBean> list, String file_type);
}
