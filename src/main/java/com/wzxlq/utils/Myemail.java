package com.wzxlq.utils;

import org.apache.commons.mail.HtmlEmail;

/**
 * @author wzx
 * @date 2019/6/3 - 23:28
 */
public class Myemail {
    public static void Test(){
        try {
            HtmlEmail email = new HtmlEmail();//不用更改
            email.setHostName("smtp.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setCharset("UTF-8");
            email.addTo("1099393970@qq.com");// 收件地址

            email.setFrom("1099393970@qq.com", "aa");//此处填邮箱地址和用户名,用户名可以任意填写

            email.setAuthentication("1099393970@qq.com", "uplmmnspagycbahb");//此处填写邮箱地址和客户端授权码

            email.setSubject("孙大大通讯");//此处填写邮件名，邮件名可任意填写
            email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + "code");//此处填写邮件内容

            email.send();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        Test();
//    }
}
