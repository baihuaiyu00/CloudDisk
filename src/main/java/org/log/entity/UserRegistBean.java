package org.log.entity;

/**
 * Created by Admin on 2016/4/16.
 */
public class UserRegistBean {

    private String username;
    private String password;
    private String qq;

    public String getUsername() {return username;}
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public String toString() {
        return "UserRegistBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", qq='" + qq + '\'' +
                '}';
    }
}
