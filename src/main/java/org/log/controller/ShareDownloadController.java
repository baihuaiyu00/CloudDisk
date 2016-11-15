package org.log.controller;

import org.log.entity.FileShareBean;
import org.log.entity.UserLoginBean;
import org.log.service.FileShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by Admin on 2016/5/12.
 */

@Controller
public class ShareDownloadController {

    @Autowired
    private FileShareService fileShareService;

    /**
     * 提取输入提取码界面需要的信息: 分享用户 文件名
     *
     * @param request
     * @return
     */
    @RequestMapping("/s")
    public String showShareFile(HttpServletRequest request) {
        String md5code = request.getParameter("check");//获取到代表文件的MD5代码
        FileShareBean fileShareBean =  fileShareService.findInfo(md5code);
        System.out.println(fileShareBean);
        request.getSession().setAttribute("fileShareBean",fileShareBean);
        return "page/fileShare";
    }

    /**
     * 进行提取码的验证
     * 若正确 进入下载资源资源页面    若错误 提示信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/scheck")
    public void checkShareFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        String checkCode = request.getParameter("code");

        FileShareBean fileShareBean = (FileShareBean) request.getSession().getAttribute("fileShareBean");
        if(fileShareBean.getCode().equals(checkCode))
        {
            System.out.println("return 1");
            pw.write("1");
        }
        else {
            System.out.println("return 0");
            pw.write("0");
        }
    }

    @RequestMapping("/sDownload")
    public void downloadShareFile(HttpServletRequest request,HttpServletResponse response) throws IOException {

        FileShareBean fileShareBean = (FileShareBean) request.getSession().getAttribute("fileShareBean");


        /**
         *  文件下载代码 无需其他内容
         */
        OutputStream out = response.getOutputStream();
        response.setContentType("text/html;charset=UTF-8");
        File file = new File(fileShareBean.getRealPath()+"/"+fileShareBean.getFileName());

        if(!(file.exists())){
            return;

        }
        InputStream in = new FileInputStream(file);
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileShareBean.getFileName(),"UTF-8"));
        byte b[] = new byte[1024];
        int len = -1;
        while((len=in.read(b))!=-1){
            out.write(b,0,len);
        }
        in.close();
    }

}
