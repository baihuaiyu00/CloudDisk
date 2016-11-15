package org.log.service;

import org.log.entity.UserLoginBean;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/4/19.
 */

@Service
public interface UserLoginService {
    boolean userLogin(UserLoginBean userLoginBean);
}
