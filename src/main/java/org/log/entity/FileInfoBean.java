package org.log.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Created by Admin on 2016/4/23.
 */
public class FileInfoBean implements Serializable {
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
