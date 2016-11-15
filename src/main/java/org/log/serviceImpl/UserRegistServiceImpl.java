package org.log.serviceImpl;

import org.log.entity.UserRegistBean;
import org.log.exception.UsernameExistException;
import org.log.mapper.UserRegistMapper;
import org.log.service.UserRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by Admin on2016/4/16.
        */
@Service
public class UserRegistServiceImpl implements UserRegistService {



   @Autowired
   private UserRegistMapper aum;

   public void addUser(UserRegistBean userRegistBean) throws UsernameExistException {
//       System.out.println("user in serviceImpl:"+u);

       UserRegistBean us = aum.checkUsername(userRegistBean.getUsername());
       if(us!=null) {
           throw new UsernameExistException("用户名已经存在");

       }
         aum.addUser(userRegistBean);
   }
}
