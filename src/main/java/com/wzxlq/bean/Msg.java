package com.wzxlq.bean;

/**
 * @author 王照轩
 * @date 2019/12/12 - 20:27
 */

import java.util.HashMap;
import java.util.Map;

public class Msg {

    private int code;
    //提示信息
    private String msg;
    //用户要返回给浏览器的数据
    private Map<String, Object> extend = new HashMap<>();

    public static Msg success() {
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("deal success!");
        return result;
    }
    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("deal fail!");
        return result;
    }
    public 	Msg add(String key,Object value) {
        this.getExtend().put(key, value);
        return this;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Map<String, Object> getExtend() {
        return extend;
    }
    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}

