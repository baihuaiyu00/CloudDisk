package org.log.serviceImpl;

import org.log.entity.UserLoginBean;
import org.log.mapper.UserLoginMapper;
import org.log.service.UserLoginService;
import org.log.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/4/19.
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginMapper ulm;


    public boolean userLogin(UserLoginBean userLoginBean) {

//        System.out.println(userLoginBean.getUsername()+"----->"+userLoginBean.getPassword());

        String input = MD5Util.encoder(userLoginBean.getPassword());
        String output = ulm.getPwdByUsername(userLoginBean);
        if(input.equals(output))
            return true;
        else
            return false;

    }
}
