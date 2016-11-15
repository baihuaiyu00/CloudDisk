package org.log.serviceImpl;

import org.log.controller.FileUploadController;
import org.log.entity.FileDelBean;
import org.log.mapper.FileOperationMapper;
import org.log.service.FileDelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Admin on 2016/5/18.
 */

@Service
public class FileDelServiceImpl implements FileDelService{

    @Autowired
    private FileOperationMapper fileDelMapper;

    public void delFile(FileDelBean fileDelBean) {
        InputStream in = FileUploadController.class.getClassLoader().getResourceAsStream("/path.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String pathGet = properties.getProperty("dirpath");

        File file = new File(fileDelBean.getFilePath()+pathGet+fileDelBean.getFileName());
        Boolean result = file.delete();
        fileDelMapper.delFile(fileDelBean);
    }
}
