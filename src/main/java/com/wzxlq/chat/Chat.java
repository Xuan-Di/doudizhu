package com.wzxlq.chat;

import com.wzxlq.controller.StartGameController;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author 王照轩
 * @date 2019/12/11 - 18:52
 */
@Service
public class Chat {
    private static CopyOnWriteArrayList<Channle> all = new CopyOnWriteArrayList<>();

//    public static PoKer poKer = new PoKer();
//    static CyclicBarrier barrier = new CyclicBarrier(3);

//    public static void start() throws IOException, InterruptedException {
//        ServerSocket server = new ServerSocket(9106);
//        System.out.println("-------server---------");
//
//        while (true) {
//            Socket client = server.accept();
//            System.out.println(" one client connection.");
//            String name = StartGameController.map1.get(client);
//            Channle c = new Channle(client, name);
//            all.add(c);
//            new Thread(c).start();
//        }
//    }

    static class Channle implements Runnable {
        private DataInputStream dis = null;
        private DataOutputStream dos = null;
        private Socket client;
        private boolean isRunning;
        private String name;

        public Channle(Socket client, String name) {
            try {
                this.client = client;
                dos = new DataOutputStream(client.getOutputStream());
                dis = new DataInputStream(client.getInputStream());
                isRunning = true;
                this.name = name;
            } catch (IOException e) {
                release();
            }

        }

        private String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
                System.out.println("*******************"+msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return msg;
        }

        private void send(String msg) {
            try {
                System.out.println("))))))))))))))"+msg);
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                release();
            }
        }

        //群聊
        private void sendOthers(String msg, boolean flag) {
            //@lq:msg
            boolean isPrivate = msg.startsWith("@");
            if (isPrivate) {
                int idx = msg.indexOf(":");
                String targetName = msg.substring(1, idx);
                msg = msg.substring(idx + 1);
                for (Channle other : all) {
                    if (other.name.equals(targetName)) {
                        other.send(this.name + "tell you:" + msg);
                    }
                }
            } else {
                for (Channle other : all) {
                    if (flag == false)
                        other.send(this.name + "tell all:" + msg);
                    else
                        other.send(msg);
                }
            }
        }
        private void release() {
            this.isRunning = false;
            Utiles.close(dis, dos);
            all.remove(this);
            sendOthers(this.name + "退出了游戏.", true);
        }

        @Override
        public void run() {
            while (isRunning) {
                String msg = receive();
                if (!msg.equals("")) {
                    sendOthers(msg, false);
                }
            }
        }
    }
}
