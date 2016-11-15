package org.log.serviceImpl;

import org.log.entity.FileUploadBean;
import org.log.mapper.FileOperationMapper;
import org.log.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/4/26.
 */

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileOperationMapper fum;

    public void fileUploadSQL(FileUploadBean fileUploadBean) {
        System.out.println("file in service"+fileUploadBean);
        fum.fileUpload(fileUploadBean);
    }
}
