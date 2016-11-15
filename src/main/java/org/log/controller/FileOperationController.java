package org.log.controller;

import org.log.entity.*;
import org.log.service.FileDelService;
import org.log.service.FileListService;
import org.log.service.FileShareService;
import org.log.service.FileUploadService;
import org.log.util.MD5Util;
import org.log.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockJspWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Admin on 2016/5/23.
 */

@Controller
@RequestMapping("/file")
public class FileOperationController {

    @Autowired
    private FileListService fls;

    @Autowired
    private FileShareService fileShareService;

//    @Autowired
//    private FileUploadService fus;

    @Autowired
    private FileDelService fileDelService;

    @Autowired
    private UserLoginBean userLoginBean;

    /**
     * 文件下载
     * 需要前端传递数据fileName 并从session中获取到username 以得到文件的下载路径
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping("/download")
    public void downloadFile(HttpServletResponse response, HttpServletRequest request) throws IOException {
        OutputStream out = response.getOutputStream();

        String fileName = request.getParameter("fileName");
        fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
        String pfileName = fileName;
        UserLoginBean userLoginBean = (UserLoginBean) request.getSession().getAttribute("userLoginBean");

        InputStream ins = FileUploadController.class.getClassLoader().getResourceAsStream("/path.properties");
        Properties properties = new Properties();
        properties.load(ins);
        String pathGet = properties.getProperty("dirpath");
//        String pathType=pathGet+userLoginBean.getUsername()+"";
//        String path = "E:\\usr/"+userLoginBean.getUsername()+""; //Windows
//        String path = "/usrfile/"+userLoginBean.getUsername()+"";//Linux
//        fileName = PathUtil.makeStorePath(path,fileName)+"/"+fileName;//此处的fileName已经代表了文件的路径
        FileShareBean fileShareBean = new FileShareBean();
        fileShareBean.setUserName(userLoginBean.getUsername());
        fileShareBean.setFileName(fileName);
        String path = fileShareService.fileSahre(fileShareBean)+pathGet+fileName;
        System.out.println("下载时得到的路径为:"+path);


        /**
         *  文件下载代码 无需其他内容
         */
        response.setContentType("text/html;charset=UTF-8");
        File file = new File(path);
        if(!(file.exists())){
            out.write("文件不存在或已经被分享者删除！".getBytes("UTF-8"));
            return;
        }

        InputStream in = new FileInputStream(file);
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(pfileName,"UTF-8"));
        byte b[] = new byte[1024];
        int len = -1;
        while((len=in.read(b))!=-1){
            out.write(b,0,len);
        }
        in.close();
        out.write("下载成功".getBytes("UTF-8"));
    }


    /**
     * 获得session封装userLoginBean
     * 使用userloginbean获得filelistbean列表
     * 把list放到JSP页面
     * @param request
     * @return
     */

    @RequestMapping("/showAll")
    public String showFiles(HttpServletRequest request) {
        String file_type = "all";
        if(request.getParameter("type")!=null)
            file_type = request.getParameter("type");

        UserLoginBean userLoginBean = (UserLoginBean) request.getSession().getAttribute("userLoginBean");
        List<FileListBean> list = fls.showFiles(userLoginBean);

        list = fls.chooseType(list,file_type);

        request.setAttribute("list",list);

        return "page/showAll";
    }


    /**
     * 文件分享
     * @param request
     * @return
     */
    @RequestMapping("/share")
    public String shareFile(HttpServletRequest request){

        HttpSession session = request.getSession();
        Object token = request.getSession().getAttribute("token");
        String tokenValue = request.getParameter("token");

        if(token!=null && token.equals(tokenValue)){
            session.removeAttribute("token");
            System.out.println("表单没有重复提交！已经移除token");
        }else{
//            request.setAttribute("submitError","submitError");
            System.out.println("表单重复提交！");
        }


        /**
         * 下列代码通过获得的信息: username fileName 获得 realPath 封装到 fileShareBean 对象中
         * 项目中的下载或是分享 需要的参数都是 fileName
         *
         */
        String filename = request.getParameter("fileName");
        try {
            filename = new String(filename.getBytes("ISO-8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        UserLoginBean userLoginBean = (UserLoginBean) request.getSession().getAttribute("userLoginBean");
        FileShareBean fileShareBean = new FileShareBean();

        fileShareBean.setFileName(filename);
        fileShareBean.setUserName(userLoginBean.getUsername());
        String filePathnew = fileShareService.fileSahre(fileShareBean);
        fileShareBean.setRealPath(filePathnew);

        /**
         * 现在要给用户一个路径 与 一个提取码
         */
        Random r = new Random();
        String chars = "abcdefghijklmnopqrstuvwxyz";
        String code = String.valueOf(r.nextInt(10))+chars.charAt((int)(Math.random() * 26))+String.valueOf(r.nextInt(10))+chars.charAt((int)(Math.random() * 26))+"";
//        System.out.println(code);

        String shareWebsite = code;
        fileShareBean.setCode(code);

//        System.out.println(request.getContextPath());
        fileShareBean.setShareWebsite(shareWebsite);


//        System.out.println(shareWebsite);
        fileShareService.addShareFile(fileShareBean);

        /**
         * 封装数据
         */
        shareWebsite = "http://www.yangjing1007.cn"+request.getContextPath()+"/s?check="+shareWebsite;
        System.out.println(fileShareBean);
        request.setAttribute("shareWebsite",shareWebsite);
        request.setAttribute("code",code);
//        request.setAttribute("fileShareBean",fileShareBean);


        return "page/makeShare";
    }


    /**
     * 删除文件
     * 删除文件在数据库中的记录并删除文件(文件目录没有删除)
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/del")
    public void delFile(HttpServletRequest request,HttpServletResponse response) throws IOException {

        UserLoginBean userLoginBean = (UserLoginBean) request.getSession().getAttribute("userLoginBean");
        String fileName = new String(request.getParameter("fileName").getBytes("ISO-8859-1"),"utf-8");


//        String storePath = PathUtil.makeStorePath(pathGet+userLoginBean.getUsername(),fileName)+"\\"+fileName;
        FileShareBean fileShareBean = new FileShareBean();
        fileShareBean.setFileName(fileName);
        fileShareBean.setUserName(userLoginBean.getUsername());
//        String storePath = fileShareService.fileSahre(fileShareBean);

        System.out.println("要删除的文件的路径为:");
        System.out.println("删除了的文件的名字是:"+fileName);

        FileDelBean fileDelBean = new FileDelBean();
        fileDelBean.setFilePath(fileShareService.fileSahre(fileShareBean));
        fileDelBean.setUsername(userLoginBean.getUsername());
        fileDelBean.setFileName(fileName);

        fileDelService.delFile(fileDelBean);




        try {
            request.getRequestDispatcher("/file/showAll").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

}
