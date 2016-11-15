package org.log.controller;

import org.log.entity.FileUploadBean;
import org.log.entity.FileInfoBean;
import org.log.entity.UserLoginBean;
import org.log.service.FileUploadService;
import org.log.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Admin on 2016/4/23.
 */
@Controller
public class FileUploadController {

    @Autowired
    private FileUploadService fus;


    @RequestMapping("/html5.htm")
    public String inputProduct(){
        return "/page/fileupload";
    }

    @RequestMapping("file_upload")
    public void saveFile(HttpServletRequest request, @ModelAttribute FileInfoBean fileInfoBean) throws IOException {
        FileUploadBean fileUploadBean = new FileUploadBean();
        MultipartFile multipartFile = fileInfoBean.getMultipartFile();
        UserLoginBean userLoginBean = (UserLoginBean) request.getSession().getAttribute("userLoginBean");

        /**
         *  封装 fileUploadBean 对象
         */
        String fileName = multipartFile.getOriginalFilename();
        fileUploadBean.setFileType(multipartFile.getContentType());
        fileUploadBean.setFileSize(multipartFile.getSize()/1024);
        fileUploadBean.setFileName(fileName);
        fileUploadBean.setUsername(userLoginBean.getUsername());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        fileUploadBean.setUpdateTime(sdf.format(new Date()));

        /**
         * 为降低耦合度，从配置文件path.properties中提取文件存储路径信息
         * 修改时 只需修改配置文件即可对整个项目生效
         */
        InputStream in = FileUploadController.class.getClassLoader().getResourceAsStream("/path.properties");
        Properties properties = new Properties();
        properties.load(in);
        String pathGet = properties.getProperty("dirpath");
        String pathBase = properties.getProperty("basepath");

        System.out.println("从配置文件中取出的路径"+pathGet);

        String path = pathBase+pathGet+userLoginBean.getUsername()+"";
//        String path = "E:\\usr\\"+userLoginBean.getUsername()+""; //Windows
//        String path = "/usrfile/"+userLoginBean.getUsername()+""; //Linux
        String storePath = PathUtil.makeStorePath(path,fileName);
//        System.out.println(storePath);
        fileUploadBean.setFilePath(storePath);

        /**
         * 载入数据库
         */
        fus.fileUploadSQL(fileUploadBean);

        /**
         *
         * 这是修改版的存储文件 计划是存储在计算机上而不是WEBAPP里 进而维护时文件依旧存在
         * 功能基本实现!
         * PathUtil 工具类用来获取HashCode来分类文件（安全性） 和 创建文件目录
         */
        try {
            File file = new File(storePath+"/"+fileName);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
