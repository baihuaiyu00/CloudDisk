package org.log.mapper;

import org.log.entity.UserRegistBean;

/**
 * Created by Admin on 2016/4/16.
 */
public interface UserRegistMapper extends SqlMapper {
    void addUser(UserRegistBean userInfoBean);

    UserRegistBean checkUsername(String username);
}
