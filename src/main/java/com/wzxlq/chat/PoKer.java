package com.wzxlq.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author 王照轩
 * @date 2019/12/11 - 14:18
 */
public class PoKer {

   static HashMap<Integer, String> poker = new HashMap<>();
    static ArrayList<Integer> pokerIndex = new ArrayList<>();
    static String[] colors = {"♥", "♣", "♦", "♠"};
    static String[] numbers = {"2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3"};
    static ArrayList<Integer> play01 = new ArrayList<>();
    static ArrayList<Integer> play02 = new ArrayList<>();
    static ArrayList<Integer> play03 = new ArrayList<>();
    static ArrayList<Integer> dipai = new ArrayList<>();
    public HashMap<Integer, String> getPoker() {
        return poker;
    }
    public PoKer() {
        poker();
    }

    public static void poker() {
        int index = 0;
        poker.put(index, "大王");
        pokerIndex.add(index);
        index++;
        poker.put(index, "小王");
        pokerIndex.add(index);
        index++;
        for (String number : numbers) {
            for (String color : colors) {
                poker.put(index, color + number);
                pokerIndex.add(index);
                index++;
            }
        }
        Collections.shuffle(pokerIndex);
        for (int i = 0; i < pokerIndex.size(); i++) {
            if (i >= 51) {
                dipai.add(pokerIndex.get(i));
                continue;
            }
            if (i % 3 == 0) {
                play01.add(pokerIndex.get(i));
            }
            if (i % 3 == 1) {
                play02.add(pokerIndex.get(i));
            }
            if (i % 3 == 2) {
                play03.add(pokerIndex.get(i));
            }

        }
        Collections.sort(play01);
        Collections.sort(play02);
        Collections.sort(play03);
        Collections.sort(dipai);
//        lookPoker("王照轩", poker, play01);
//        lookPoker("李倩", poker, play02);
//        lookPoker("张三", poker, play03);
        lookPoker("底牌", poker, dipai);

    }
     public  ArrayList<Integer> getPoker(String name){
        if(name.equals("a")){
            return play01;
        }
        if(name.equals("b")){
            return play02;
        }
        if(name.equals("c")){
            return play03;
        }
       return dipai;
     }
    public  synchronized static void lookPoker(String name, HashMap<Integer, String> poker, ArrayList<Integer> list) {
        System.out.print(name + ":");
        for (Integer key : list) {
            String velue = poker.get(key);
            System.out.print(velue + " ");
        }
        System.out.println();
    }
    public synchronized static String look(HashMap<Integer, String> poker,ArrayList<Integer> list){
        StringBuffer sb = new StringBuffer();
        for (Integer key : list) {
            String velue = poker.get(key);
            sb.append(velue+" ");
        }
        return sb.toString();
    }
}
