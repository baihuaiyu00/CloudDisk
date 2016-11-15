package org.log.service;

import org.log.entity.FileUploadBean;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/4/26.
 */

@Service
public interface FileUploadService {
    void fileUploadSQL(FileUploadBean fileUploadBean);
}
