package org.log.serviceImpl;

import org.log.entity.FileUploadBean;
import org.log.mapper.ShareKeepMapper;
import org.log.service.ShareKeepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016/6/6.
 */
@Service
public class ShareKeepServiceImpl implements ShareKeepService {

    @Autowired
    private ShareKeepMapper shareKeepMapper;

    public FileUploadBean findFileByLink(String shareWebsite) {
//        System.out.println("ServiceImpl中的shareWebsite="+shareWebsite);
        FileUploadBean fileUploadBean = shareKeepMapper.findFileByLink(shareWebsite);
//        System.out.println("保存至我的云盘时在数据库中取得的文件信息为:"+fileUploadBean.toString());
//        System.out.println(shareKeepMapper.findFileByLink(shareWebsite));
        return fileUploadBean;

    }
}
