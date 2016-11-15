package org.log.entity;

/**
 * Created by Admin on 2016/5/9.
 */
public class FileShareBean {

    private String fileName;
    private String userName;
    private String realPath;
    private String code;
    private String shareWebsite;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShareWebsite() {
        return shareWebsite;
    }

    public void setShareWebsite(String shareWebsite) {
        this.shareWebsite = shareWebsite;
    }

    @Override
    public String toString() {
        return "FileShareBean{" +
                "fileName='" + fileName + '\'' +
                ", userName='" + userName + '\'' +
                ", realPath='" + realPath + '\'' +
                ", code='" + code + '\'' +
                ", shareWebsite='" + shareWebsite + '\'' +
                '}';
    }
}
