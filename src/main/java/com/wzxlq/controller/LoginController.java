package com.wzxlq.controller;

import com.wzxlq.bean.Msg;
import com.wzxlq.bean.User;
import com.wzxlq.service.LoginService;
import com.wzxlq.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wzx
 * @date 2019/6/1 - 19:48
 */
@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    String emailcode;
    public static int peopleCount=0;
    /**
     * 登录
     */
    @RequestMapping("/queryUser")
    public String queryUser(@RequestParam("uname") String name, @RequestParam("pwd") String pw, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        System.out.println("连接登录");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        User user = new User(name, pw);
        int m = loginService.queryUser(user);
        System.out.println(m);
        System.out.println("哈哈哈");
        if (m >= 1) {
            //return "success";
            //request.getRequestDispatcher("/views/success.jsp").forward(request, response);
            //Myemail.Test();
            request.getSession().setAttribute("uname", name);
            peopleCount++;
            response.sendRedirect("mainpage.jsp");
            return null;
        }
        response.sendRedirect("index.jsp");
        return null;
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/addUserAjax")
    @ResponseBody
    public Msg addUserAjax(User user) {
        System.out.println(user.getUserName() + "-----" + user.getPasswd() + "---" + user.getEmail());
        loginService.updatePw(user);
        boolean m = loginService.check(user);
        if (m == true) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }

    /**
     * 发送邮箱
     *
     * @param email1
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/EmailServlet2")
    public void update(@RequestParam("uemail") String email1, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(email1);
        emailcode = EmailUtils.sendEamilCode(email1);
        //Myemail.Test();
    }

    /**
     * 提交找回密码的表单
     *
     * @param name
     * @param email
     * @param pwd
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/UpdateServlet")
    public void update02(@RequestParam("username") String name, @RequestParam("uemail") String email, @RequestParam("upasswd") String pwd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new User(name, pwd, email);
        loginService.updatePw(user);
        response.sendRedirect("index.jsp");
        //Myemail.Test();
    }

    /**
     * 验证验证码是否正确
     *
     * @param test
     * @param response
     * @throws IOException
     */
    @RequestMapping("/Match")
    public void match(@RequestParam("test") String test, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        if (test.equals(emailcode)) {
            out.write("true");
        } else {
            out.write("false");
        }
        out.close();
    }
}






