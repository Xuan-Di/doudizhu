package com.wzxlq.service;

import org.springframework.stereotype.Service;

/**
 * @author 王照轩
 * @date 2019/12/12 - 21:53
 */
@Service
public class SendService {
    String msg;
    public void sendmsg(String msg){
        this.msg=msg;
    }
    public String getmsg(){
       return msg;
    }
}
