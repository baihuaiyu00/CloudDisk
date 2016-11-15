package org.log.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 2016/4/19.
 */
public class UserLoginBean implements Serializable{

    private String username;
    private String password;
    private Map<String,String> errors = new HashMap<String, String>();

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    /**
     * validate方法用于验证用户名登录信息是否为空
     * 若有空的输入项 在map errors中放入元素  没有东西 返回true、有东西 返回false
     * 原本使用errors来把得到的错误封装到View层显示 改进后换用JavaScript的alert显示 验证所得到的结果用ajax的返回值判断
     * @return errors.isEmpty()
     */
    public boolean validate(){
        if(username==null||username.trim().equals("")){
//            System.out.println("username in validate " + username);
            errors.put("username", "请输入用户名");
        }
        if(password==null){
//            System.out.println("password in validate " + password);
            errors.put("username", "请输入密码");
        }

        return errors.isEmpty();
    }


    public Map<String,String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "UserLoginBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", errors=" + errors +
                '}';
    }
}
