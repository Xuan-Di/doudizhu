package com.wzxlq.service;


import com.wzxlq.bean.User;
import com.wzxlq.dao.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wzx
 * @date 2019/6/1 - 19:50
 */
@Service
public class LoginService {
    @Autowired
    LoginMapper loginMapper;

    public int queryUser(User user){

        return  loginMapper.queryUser(user);
    }

    public void updatePw(User user) {
       loginMapper.updatePw(user);
    }

    public boolean check(User user) {
        int a=loginMapper.check(user);
        return a==1;
    }
}
