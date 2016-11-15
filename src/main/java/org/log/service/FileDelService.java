package org.log.service;

import org.log.entity.FileDelBean;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/5/18.
 */
@Service
public interface FileDelService {

    void delFile(FileDelBean fileDelBean);



}
