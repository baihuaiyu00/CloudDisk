package org.log.controller;

import org.log.entity.UserLoginBean;
import org.log.entity.UserRegistBean;
import org.log.exception.UsernameExistException;
import org.log.service.UserLoginService;
import org.log.service.UserRegistService;
import org.log.util.MD5Util;
import org.log.util.WebFormBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Admin on 2016/4/19.
 */

@Controller
@RequestMapping("/User")
public class UserOperationController {

    @Autowired
    private UserLoginService uls;

    @Autowired
    private UserRegistService as;

    @RequestMapping("/login.htm")
    public void userLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String result;
            UserLoginBean userLoginBean = WebFormBeanUtils.fillFormBean(request, UserLoginBean.class);

//            System.out.println("userLoginBean in Controller is " + userLoginBean);
            if (!userLoginBean.validate()) { //userLoginBean为空 返回true 说明输入了username与password
                System.out.println("running the null_choice");
                request.setAttribute("userLoginBean", userLoginBean);
                result = "2";
            } else {
                if (uls.userLogin(userLoginBean)) {
                    request.getSession().setAttribute("userLoginBean", userLoginBean);
                    result = "1";
                } else {
                    request.setAttribute("userLoginBean", userLoginBean);
                    result = "0";
                }
            }

            PrintWriter pw = response.getWriter();
            pw.write(result);
    }

    @RequestMapping("/logout.htm")
    public String userLogout(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.getSession().removeAttribute("userLoginBean");
        response.setHeader("Refresh", "2;URL="+request.getContextPath());
        request.setAttribute("message", "注销成功！");
        return "/page/message2";
    }

    @RequestMapping("/add.htm")
    public String addUser(HttpServletRequest request, HttpServletResponse response){
        /**
         * 获取信息封装userRegistBean
         */
        UserRegistBean userRegistBean = new UserRegistBean();
        userRegistBean.setUsername(request.getParameter("user_l"));
        userRegistBean.setPassword(MD5Util.encoder(request.getParameter("passwd_l")));
        userRegistBean.setQq(request.getParameter("qq"));
        request.setAttribute("user",userRegistBean);
        System.out.println("user in controller"+userRegistBean);
        try {
            as.addUser(userRegistBean);
        } catch (UsernameExistException e) {
            response.setHeader("Refresh", "2;URL="+request.getContextPath());
            return "/page/message2";
        }
        return "page/index";
    }

}
