package org.log.service;

import org.log.entity.FileUploadBean;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/6/6.
 */
@Service
public interface ShareKeepService {

    FileUploadBean findFileByLink(String shareWebsite);

}
