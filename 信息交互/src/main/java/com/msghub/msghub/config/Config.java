package com.msghub.msghub.config;

import java.util.ArrayList;
import java.util.List;


public class Config {

    public static int count = 0;


    public static final String REGEX6 = "[1-9]\\d{7,10}@qq\\.com";//邮箱正则

    public static final String WHETHER = "1"; // 未开通

    public static final String cellphone = "1"; // 1发送策略

    public static final String WECHAT = "2"; // 2发送策略

    public static final String MAIL = "3"; // 3发送策略

    public static List<String> list = new ArrayList<>();//初始短信所有的模板变量

    public static final String Lasttime = "GQSJ"; // 过期时间

    public static final String Authenticationcode = "YZM"; // 验证码

    public static List<String> getList() {
        return list;
    }

    public static void setList(List<String> list) {
        Config.list = list;
    }

    static {
        list.add(Lasttime);
        list.add(Authenticationcode);
    }

}
