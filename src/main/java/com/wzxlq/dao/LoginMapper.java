package com.wzxlq.dao;


import com.wzxlq.bean.User;

/**
 * @author wzx
 * @date 2019/6/1 - 20:01
 */
public interface LoginMapper {
    int queryUser(User user);

    int updatePw(User user);

    int check(User user);
}
