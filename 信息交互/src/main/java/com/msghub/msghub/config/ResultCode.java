package com.msghub.msghub.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回 状态码 信息
 *
 */

public class ResultCode {


    @Component
    @Configuration      //1.主要用于标记配置类，兼备Component的效果。
    @EnableScheduling   // 2.开启定时任务
    public class SaticScheduleTask {
        //3.添加定时任务
        @Scheduled(cron = "0/5 * * * * ?")
        //或直接指定时间间隔，例如：5秒
        //@Scheduled(fixedRate=5000)
        private void configureTasks() {
            System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        }
    }

    public static Map<Integer, Object> map = new HashMap();

    public static Map<Integer, Object> getMap() {
        return map;
    }

    public static void setMap(Map<Integer, Object> map) {
        ResultCode.map = map;
    }

    static {
        map.put(0, "发送成功");
        map.put(1, "发送类型错误");
        //手机返回状态码
        map.put(100001, "未传参数");
        map.put(100002, "公司id空");
        map.put(100003, "模板id空 ");
        map.put(100007, "模板变量空");
        map.put(100008, "接收人空");
        map.put(100009, "接收人user空");
        map.put(100004, "业务未开通 公司参数不正确");
        map.put(100005, "余额不足  或未正确公司参数");
        map.put(100006, "模板拼接错误");
        map.put(100012, "手机号码为空");
        map.put(-100001, "鉴权不通过,请检查账号,密码");
        map.put(-100002, "用户多次鉴权不通过,请检查帐号,密码");
        map.put(-100003, "发送平台余额不足");
        map.put(-100004, "账号或者密码字段填写不合法");
        map.put(-100011, "短信内容超长");
        map.put(-100012, "手机号码不合法");
        map.put(-100014, "手机号码超过最大支持数量 1000");
        map.put(-100029, "端口绑定失败");
        map.put(-100057, "用户账号登录的连接数超限");
        //微信返回的状态码
        map.put(-40001, "微信推送 内部错误");
        map.put(-40003, "openId为空");
        map.put(-41002, "微信账号为空");
        map.put(-41004, "微信密码不正确");
        /* 邮箱*/
        map.put(350001, "找不到 模板 或者 id为空");
        map.put(350002, "模板变量为空");
        map.put(350003, "接收人为空");
        map.put(100086, "格式错误");
    }
}
