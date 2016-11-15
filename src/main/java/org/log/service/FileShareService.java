package org.log.service;

import org.log.entity.FileShareBean;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/5/9.
 */

@Service
public interface FileShareService {

    String fileSahre(FileShareBean fileShareBean);

    void addShareFile(FileShareBean fileShareBean);

    FileShareBean findInfo(String md5code);
}
