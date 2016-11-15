package org.log.service;

import org.log.entity.UserRegistBean;
import org.log.exception.UsernameExistException;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/4/16.
 */

@Service
public interface UserRegistService {

     void addUser(UserRegistBean u) throws UsernameExistException;
}
