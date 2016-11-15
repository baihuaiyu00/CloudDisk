package org.log.serviceImpl;

import org.log.entity.FileShareBean;
import org.log.mapper.FileOperationMapper;
import org.log.service.FileShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/5/9.
 */
@Service
public class FileShareServiceImpl implements FileShareService {

    @Autowired
    private FileOperationMapper fileShareMapper;

    /**
     *
     * @param fileShareBean
     * @return
     */
    public String fileSahre(FileShareBean fileShareBean) {

        return fileShareMapper.findPathByUsername(fileShareBean);
    }

    /**
     *
     * @param fileShareBean
     */
    public void addShareFile(FileShareBean fileShareBean) {
        fileShareMapper.addShareFile(fileShareBean);
    }

    /**
     *
     * @param md5code
     * @return
     */
    public FileShareBean findInfo(String md5code) {
        FileShareBean fileShareBean = fileShareMapper.findInfoByCode(md5code);
        fileShareBean.setShareWebsite(md5code);
        return fileShareBean;
    }

}

