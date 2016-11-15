package org.log.mapper;


import org.log.entity.UserLoginBean;

/**
 * Created by Admin on 2016/4/19.
 */
public interface UserLoginMapper extends SqlMapper {

    String getPwdByUsername(UserLoginBean userLoginBean);

}
