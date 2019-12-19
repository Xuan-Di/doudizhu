package com.wzxlq.controller;

import com.wzxlq.bean.Msg;
import com.wzxlq.chat.PoKer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 王照轩
 * @date 2019/12/12 - 12:22
 */
@Controller
public class StartGameController {
    //那个消息队列
    public static LinkedList<String> msgList1 = new LinkedList<>();
    public static LinkedList<String> msgList2 = new LinkedList<>();
    public static LinkedList<String> msgList3 = new LinkedList<>();
    //三个玩家的将要吃的牌的队列(别人打的牌会放到你的队列里)
    public static ArrayList<Integer> asendpoke = new ArrayList<>();
    public static ArrayList<Integer> bsendpoke = new ArrayList<>();
    public static ArrayList<Integer> csendpoke = new ArrayList<>();
    static PoKer poKer = new PoKer();
    //底牌
    ArrayList<Integer> dipai = poKer.getPoker("dipai");
    //存三个玩家的牌
    HashMap<String, ArrayList<Integer>> userpoke = new HashMap<>();
    //三把锁
    static volatile boolean alock = false;
    static volatile boolean block = false;
    static volatile boolean clock = false;
    static volatile boolean onlylock = false;
    //抢地主
    @RequestMapping("/qiangdizhu")
    @ResponseBody
    public void qiangdizhu(@RequestParam("uname") String name) {
        if (onlylock == true)
            return;
        if (name.equals("a"))
            alock = true;
        if (name.equals("b"))
            block = true;
        if (name.equals("c"))
            clock = true;
        //抢了地主后,把三张底牌给地主
        ArrayList<Integer> dizhupokeList = userpoke.get(name);
        dizhupokeList.addAll(dipai);
        dipai = null;
        onlylock = true;
    }
    //吃不起
    @RequestMapping("/dontSend")
    @ResponseBody
    public Msg dontsend(@RequestParam("uname") String name) {
        if (name.equals("a") && alock == false)
            return Msg.success().add("dontclick", "别放弃!还没轮到你!");
        if (name.equals("b") && block == false)
            return Msg.success().add("dontclick", "别放弃!还没轮到你!");
        if (name.equals("c") && clock == false)
            return Msg.success().add("dontclick", "别放弃!还没轮到你!");

        if (name.equals("a") && alock == true) {
            if (asendpoke.get(0) == -1) {
                return Msg.success().add("dontclick", "没人的牌大过你,请继续出牌");
            }
            alock = false;
            block = true;
        }
        if (name.equals("b") && block == true) {
            if (bsendpoke.get(0) == -1) {
                return Msg.success().add("dontclick", "没人的牌大过你,请继续出牌");
            }
            block = false;
            clock = true;
        }
        if (name.equals("c") && clock == true) {
            if (csendpoke.get(0) == -1) {
                return Msg.success().add("dontclick", "没人的牌大过你,请继续出牌");
            }
            clock = false;
            alock = true;
        }
        msgList1.addLast(name + "放弃了出牌");
        msgList2.addLast(name + "放弃了出牌");
        msgList3.addLast(name + "放弃了出牌");
        return Msg.success().add("dontclick", "放弃成功");
    }
    //出牌
    @RequestMapping("/sendpoke")
    @ResponseBody
    public Msg sendpoke(@RequestBody Map<String, String> map) {
        String name = "";
        String pokearray = "";
        if (map.containsKey("uname")) {
            name = map.get("uname");
        }
        if (map.containsKey("sendpokearray")) {
            pokearray = map.get("sendpokearray") + ",";
        }
        if (name.equals("a")) {
            //当被锁住时,想出牌
            if (alock == false) {
                return Msg.success().add("lname", name).add("newpoke", userpoke.get(name))
                        .add("less", "还没轮到您出牌.");
            }
        }
        if (name.equals("b")) {
            if (block == false) {
                return Msg.success().add("lname", name).add("newpoke", userpoke.get(name))
                        .add("less", "还没轮到您出牌.");
            }
        }
        if (name.equals("c")) {
            if (clock == false) {
                return Msg.success().add("lname", name).add("newpoke", userpoke.get(name))
                        .add("less", "还没轮到您出牌.");
            }
        }
        //解析前台传过来的json数据(将要出的牌)放到list里
        ArrayList<Integer> list = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < pokearray.length(); i++) {
            if (pokearray.charAt(i) != ',') {
                stringBuffer.append(pokearray.charAt(i));
                continue;
            } else {
                String mystring = stringBuffer.toString();
                stringBuffer.delete(0, mystring.length());
                int num = Integer.parseInt(mystring);
                list.add(num);
            }
        }
        //计算将要出的牌的真正索引大小
        List<Integer> mylist = list.stream().map(i -> (i - 2) / 4).collect(Collectors.toList());
        if (name.equals("a")) {
            //当玩家不需要吃别人牌的时候(地主第一次出牌)
            if (asendpoke.get(0) == -1) {
                bsendpoke.clear();
                bsendpoke.addAll(list);
                csendpoke.clear();
                csendpoke.addAll(list);
            }
            //玩家要吃别人的牌了
            if (asendpoke.get(0) != -1) {
                //当出的牌和别人打的牌数量不一致时
                if (list.size() != asendpoke.size()) {
                    Set<Integer> set = new HashSet<>(mylist);
                    //当出的牌是炸弹或者是一对王时
                    if ((set.size() == 1 && list.size() == 4) || (list.get(0) == 1 && list.get(1) == 0)) {
                        asendpoke.clear();
                        asendpoke.add(-1);
                        bsendpoke.clear();
                        bsendpoke.addAll(list);
                        csendpoke.clear();
                        csendpoke.addAll(list);
                        ArrayList<Integer> mypokelist = userpoke.get(name);
                        //玩家现有的牌减去即将要出的牌
                        mypokelist.removeAll(list);
                        block = true;
                        alock = false;
                        return Msg.success().add("lname", name).add("newpoke", mypokelist).add("less", "ok");
                    } else {
                        return Msg.success().add("lname", name).add("newpoke", userpoke.get(name))
                                .add("less", "您的牌数量不规范.");
                    }
                }
                //当出的牌和别人打的牌数量一致时 比较出的牌真正的索引和别人打的牌真正的索引大小
                Integer newresult = list.stream().map(i -> (i - 2) / 4).reduce(0, (i, j) -> i + j);
                Integer oldresult = asendpoke.stream().map(i -> (i - 2) / 4).reduce(0, (i, j) -> i + j);
                //比较一下,如果出的牌真正索引大小>=别人打的牌真正的索引大小
                if (newresult >= oldresult) {
                    //大王吃小王(注意这里是大王索引是1,小王是0)
                    if (list.size() == 1 && list.get(0) == 0 && asendpoke.get(0) == 1) {
                        System.out.println("大王吃小王");
                        asendpoke.clear();
                        asendpoke.add(-1);
                        bsendpoke.clear();
                        bsendpoke.addAll(list);
                        csendpoke.clear();
                        csendpoke.addAll(list);
                        //大小王吃二
                    } else if (list.size() == 1 && (list.get(0) == 1 || list.get(0) == 0) && asendpoke.get(0) > 1) {
                        System.out.println("大小王吃二");
                        asendpoke.clear();
                        asendpoke.add(-1);
                        bsendpoke.clear();
                        bsendpoke.addAll(list);
                        csendpoke.clear();
                        csendpoke.addAll(list);
                    } else {
                        //出牌太小
                        return Msg.success().add("lname", name).add("newpoke", userpoke.get(name))
                                .add("less", "您的牌太小了");
                    }
                } else {
                    //出的牌完全没问题,清空自己的队列,给自己加一个-1,然后清空别人队列,给别人的队列加上我出的牌,期待别人来吃我的牌
                    asendpoke.clear();
                    asendpoke.add(-1);
                    bsendpoke.clear();
                    bsendpoke.addAll(list);
                    csendpoke.clear();
                    csendpoke.addAll(list);
                }
            }
        }
        if (name.equals("b")) {
            if (bsendpoke.get(0) == -1) {
                asendpoke.clear();
                asendpoke.addAll(list);
                csendpoke.clear();
                csendpoke.addAll(list);
            }
            if (bsendpoke.get(0) != -1) {
                if (list.size() != bsendpoke.size()) {
                    Set<Integer> set = new HashSet<>(mylist);
                    if ((set.size() == 1 && list.size() == 4) || (list.get(0) == 1 && list.get(1) == 0)) {
                        bsendpoke.clear();
                        bsendpoke.add(-1);
                        asendpoke.clear();
                        asendpoke.addAll(list);
                        csendpoke.clear();
                        csendpoke.addAll(list);
                        ArrayList<Integer> mypokelist = userpoke.get(name);
                        mypokelist.removeAll(list);
                        clock = true;
                        block = false;
                        return Msg.success().add("lname", name).add("newpoke", mypokelist).add("less", "ok");
                    } else {
                        return Msg.success().add("lname", name).add("newpoke", userpoke.get(name))
                                .add("less", "您的牌数量不规范.");
                    }
                }
                Integer newresult = list.stream().map(i -> (i - 2) / 4).reduce(0, (i, j) -> i + j);
                Integer oldresult = bsendpoke.stream().map(i -> (i - 2) / 4).reduce(0, (i, j) -> i + j);
                System.out.println(newresult + "new new");
                System.out.println(oldresult + "old old");
                if (newresult >= oldresult) {
                    if (list.size() == 1 && list.get(0) == 0 && bsendpoke.get(0) == 1) {
                        System.out.println("大王吃小王");
                        bsendpoke.clear();
                        bsendpoke.add(-1);
                        asendpoke.clear();
                        asendpoke.addAll(list);
                        csendpoke.clear();
                        csendpoke.addAll(list);
                    } else if (list.size() == 1 && (list.get(0) == 1 || list.get(0) == 0) && bsendpoke.get(0) > 1) {
                        System.out.println("大小王吃二");
                        bsendpoke.clear();
                        bsendpoke.add(-1);
                        asendpoke.clear();
                        asendpoke.addAll(list);
                        csendpoke.clear();
                        csendpoke.addAll(list);
                    } else {
                        System.out.println("wo hen xiao");
                        return Msg.success().add("lname", name).add("newpoke", userpoke.get(name))
                                .add("less", "您的牌太小了");
                    }
                } else {
                    bsendpoke.clear();
                    bsendpoke.add(-1);
                    asendpoke.clear();
                    asendpoke.addAll(list);
                    csendpoke.clear();
                    csendpoke.addAll(list);
                }
            }
        }
        if (name.equals("c")) {
            if (csendpoke.get(0) == -1) {
                asendpoke.clear();
                asendpoke.addAll(list);
                bsendpoke.clear();
                bsendpoke.addAll(list);
            }
            if (csendpoke.get(0) != -1) {
                if (list.size() != csendpoke.size()) {
                    Set<Integer> set = new HashSet<>(mylist);
                    if ((set.size() == 1 && list.size() == 4) || (list.get(0) == 1 && list.get(1) == 0)) {
                        csendpoke.clear();
                        csendpoke.add(-1);
                        asendpoke.clear();
                        asendpoke.addAll(list);
                        bsendpoke.clear();
                        bsendpoke.addAll(list);
                        ArrayList<Integer> mypokelist = userpoke.get(name);
                        mypokelist.removeAll(list);
                        alock = true;
                        clock = false;
                        return Msg.success().add("lname", name).add("newpoke", mypokelist).add("less", "ok");
                    } else {
                        System.out.println(list + "================");
                        return Msg.success().add("lname", name).add("newpoke", userpoke.get(name))
                                .add("less", "您的牌数量不规范.");
                    }
                }

                Integer newresult = list.stream().map(i -> (i - 2) / 4).reduce(0, (i, j) -> i + j);
                Integer oldresult = csendpoke.stream().map(i -> (i - 2) / 4).reduce(0, (i, j) -> i + j);
                System.out.println(newresult + "new new");
                System.out.println(oldresult + "old old");
                if (newresult >= oldresult) {
                    if (list.size() == 1 && list.get(0) == 0 && csendpoke.get(0) == 1) {
                        System.out.println("大王吃小王");
                        csendpoke.clear();
                        csendpoke.add(-1);
                        asendpoke.clear();
                        asendpoke.addAll(list);
                        bsendpoke.clear();
                        bsendpoke.addAll(list);
                    } else if (list.size() == 1 && (list.get(0) == 1 || list.get(0) == 0) && csendpoke.get(0) > 1) {
                        System.out.println("大小王吃二");
                        csendpoke.clear();
                        csendpoke.add(-1);
                        asendpoke.clear();
                        asendpoke.addAll(list);
                        bsendpoke.clear();
                        bsendpoke.addAll(list);
                    } else {
                        System.out.println("wo hen xiao");
                        return Msg.success().add("lname", name).add("newpoke", userpoke.get(name))
                                .add("less", "您的牌太小了");
                    }
                } else {
                    csendpoke.clear();
                    csendpoke.add(-1);
                    asendpoke.clear();
                    asendpoke.addAll(list);
                    bsendpoke.clear();
                    bsendpoke.addAll(list);
                }
            }
        }
        //根据名字,拿到我的手牌
        ArrayList<Integer> mypokelist = userpoke.get(name);
        //当前手牌减去我将要打的牌
        mypokelist.removeAll(list);
        System.out.println(list.toString());
        System.out.println(mypokelist.toString());
        //关闭自己的锁,开启下家的锁
        if (name.equals("a")) {
            block = true;
            alock = false;
        }
        if (name.equals("b")) {
            clock = true;
            block = false;
        }
        if (name.equals("c")) {
            alock = true;
            clock = false;
        }
        return Msg.success().add("lname", name).add("newpoke", mypokelist).add("less", "ok");
    }
    //加入游戏
    @RequestMapping("/join")
    public String join(@RequestParam("uname") String name, HttpServletResponse response, HttpServletRequest request) throws
            IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        ArrayList<Integer> pokerList = poKer.getPoker(name);
        userpoke.put(name, pokerList);
        String lookString = poKer.look(poKer.getPoker(), pokerList);
        ArrayList<Integer> dipaiList = poKer.getPoker("dipai");
        poKer.look(poKer.getPoker(), dipaiList);
        request.getSession().setAttribute("pokerList", pokerList);
        request.getSession().setAttribute("dipaiList", dipaiList);
        request.getSession().setAttribute("lookString", lookString);
        request.getSession().setAttribute("uname", name);
        return "gaming";
    }
    //发送消息
    @RequestMapping("/msg")
    @ResponseBody
    public void getMsg(@RequestParam("msg") String msg, @RequestParam("uname") String name, HttpServletRequest
            request, HttpServletResponse response) {
        msg = name + "对所有人说:" + msg;
        msgList1.addLast(msg);
        msgList2.addLast(msg);
        msgList3.addLast(msg);
    }
    //前台ajax每三秒执行一次该方法,监测有没有人发了消息和出了牌
    @RequestMapping("/recevie")
    @ResponseBody
    public Msg recevie(@RequestParam("uname") String name) {
        String result = null;
        ArrayList<Integer> arrayList = null;
        if (name.equals("a") && !msgList1.isEmpty()) {
            result = msgList1.removeFirst();
        }
        if (name.equals("b") && !msgList2.isEmpty()) {
            result = msgList2.removeFirst();
        }
        if (name.equals("c") && !msgList3.isEmpty()) {
            result = msgList3.removeFirst();
        }
        if (name.equals("a")) {
            arrayList = asendpoke;
        }
        if (name.equals("b")) {
            arrayList = bsendpoke;
        }
        if (name.equals("c")) {
            arrayList = csendpoke;
        }
        //如果我的牌消息队列为空,则加-1
        if (arrayList.isEmpty()) {
            arrayList.add(-1);
        }
        //如果我的聊天消息队列为空,则赋值为123
        if (result == null)
            result = "123";
        System.out.println("a=" + alock + " b=" + block + " c=" + clock);
        return Msg.success().add("result", result).add("dipaili", dipai)
                .add("othersend", arrayList);
    }

    @RequestMapping("/queryPeople")
    @ResponseBody
    public Msg queryPeople() {
        int peopleCount = LoginController.peopleCount;
        return Msg.success().add("peopleCount", peopleCount);
    }
}
