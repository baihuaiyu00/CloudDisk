package org.log.entity;

import java.util.Date;

/**
 * Created by Admin on 2016/4/26.
 */
public class FileListBean {

    private String fileName;
    private String fileSize;
    private String updateTime;
    private String fileType;
    private String username;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "FileListBean{" +
                "fileName='" + fileName + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", updateTime=" + updateTime +
                ", fileType='" + fileType + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
