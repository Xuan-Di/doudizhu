package com.wzxlq.chat;

import java.io.Closeable;

/**
 * @author 王照轩
 * @date 2019/12/11 - 19:22
 */
public class Utiles {
    public static void close(Closeable... targets){
        for(Closeable target:targets){
            try {
                if(null!=target)
                    target.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
