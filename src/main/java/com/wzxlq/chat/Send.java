package com.wzxlq.chat;

import com.wzxlq.service.SendService;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author 王照轩
 * @date 2019/12/11 - 19:44
 */

public class Send implements Runnable {

    private DataOutputStream dos;
    private Socket client;
    private boolean isRunning;
    private String name;
    private SendService sendService;
    public Send(Socket client,String name,SendService sendService) {
        this.sendService=sendService;
        this.name = name;
        this.isRunning = true;
        this.client = client;

        try {
            dos = new DataOutputStream(client.getOutputStream());
            send(name);
        } catch (IOException e) {
            e.printStackTrace();
            this.release();
        }
    }

    private void release() {
        this.isRunning = false;
        Utiles.close(dos, client);
    }

    @Override
    public void run() {
        while (isRunning) {
            String msg = fromAjax();
            if(!"".equals(msg)){
                send(msg);
            }
        }
    }
    public String fromAjax(){
        String msg = sendService.getmsg();
        if(msg==null){
            return "1";
        }
        return msg;
    }
    private void send(String msg){
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            release();
        }
    }
//    @RequestMapping("/message")
//    private String getFromAjax(){
//
//    }

}
