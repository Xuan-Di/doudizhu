package com.wzxlq.bean;

/**
 * @author wzx
 * @date 2019/6/1 - 19:57
 */
public class User {
    private int id;
    private  String userName;
    private String passwd;
    private String email;

    public User() {

    }

    public User(String userName, String passwd) {

        this.userName = userName;
        this.passwd = passwd;

    }

    public User(String userName, String passwd, String email) {
        this.userName = userName;
        this.passwd = passwd;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
