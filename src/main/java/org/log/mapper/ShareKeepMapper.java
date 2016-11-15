package org.log.mapper;

import org.log.entity.FileUploadBean;

/**
 * Created by Admin on 2016/6/6.
 */
public interface ShareKeepMapper extends SqlMapper {

    FileUploadBean findFileByLink(String shareWebsite);

}
