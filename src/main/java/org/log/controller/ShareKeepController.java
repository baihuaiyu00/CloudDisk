package org.log.controller;

import org.log.entity.FileShareBean;
import org.log.entity.FileUploadBean;
import org.log.entity.UserLoginBean;
import org.log.service.FileUploadService;
import org.log.service.ShareKeepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Admin on 2016/6/6.
 */
@Controller
@RequestMapping("/file")
public class ShareKeepController {

    @Autowired
    private ShareKeepService shareKeepService;

    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping("/shareKeep")
    public String shareKepp(HttpServletRequest request){
        FileShareBean fileShareBean= (FileShareBean) request.getSession().getAttribute("fileShareBean");
        String shareWebSite = fileShareBean.getShareWebsite();
        UserLoginBean userLoginBean = (UserLoginBean) request.getSession().getAttribute("userLoginBean");
//        System.out.println("Controller层的shreWebsite="+shareWebSite);
        FileUploadBean fileUploadBean = shareKeepService.findFileByLink(shareWebSite);

        if(userLoginBean!=null) {

            fileUploadBean.setUsername(userLoginBean.getUsername());
            System.out.println("Controller层准备SQL的Bean="+fileUploadBean.toString());
            fileUploadService.fileUploadSQL(fileUploadBean);
        }else {
            System.out.println("没有获取到session中的User信息");
        }



        return "page/message2";


    }
}
