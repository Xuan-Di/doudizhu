package com.wzxlq.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author 王照轩
 * @date 2019/12/11 - 19:44
 */
public class Receive implements Runnable {
    DataInputStream dis;
    private Socket client;
    private boolean isRunning;

    public Receive(Socket client) {
        this.isRunning=true;
        this.client = client;
        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            release();
        }
    }
    private String receive(){
        String msg="";
        try {
            msg=dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            release();
        }
        return msg;
    }

    @Override
    public void run() {
        while(isRunning){
            String msg = receive();
            if(!"".equals(msg)){
                System.out.println(msg);
            }
        }
    }

    private void release() {
        this.isRunning = false;
        Utiles.close(dis, client);
    }
}
