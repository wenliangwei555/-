package com.msghub.msghub.config.wechatconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 获取yml配置的  开发者账号密码
 *
 * @Component //注入ioc
 * @ConfigurationProperties 可以自动封装  配置文件配置的参数 使用get获取
 * <p>
 * * @Author 温莨
 * @Date 2020/1/31 16:02
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "parameterapp", ignoreUnknownFields = true)
public class Parameterapp {

    /**
     * 小程序appid
     */
    private String appid;
    /**
     * 小程序appSecrect
     */
    private String appsecrect;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecrect() {
        return appsecrect;
    }

    public void setAppsecrect(String appsecrect) {
        this.appsecrect = appsecrect;
    }
}
