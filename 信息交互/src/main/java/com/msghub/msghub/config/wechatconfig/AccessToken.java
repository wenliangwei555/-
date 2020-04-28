package com.msghub.msghub.config.wechatconfig;


/**
 * @Author 温莨
 *
 * @Date 2020/1/31 16:02
 * @Version 1.0
 */

public class AccessToken {

    private String accesstoken;

    private Long expiresin;

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public Long getExpiresin() {
        return expiresin;
    }

    public void setExpiresin(Long expiresin) {
        this.expiresin = expiresin;
    }
}
