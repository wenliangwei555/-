package com.msghub.msghub.model;

import org.springframework.stereotype.Component;

/**
 * 发送配置类
 */
@Component
public class Msgconfig {
    //配置 标识
    private String id;
    //账号
    private String send_id;
    //密码
    private String password;
    //公司标识
    private String client_id;
    //发送渠道标识
    private String affiliation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSend_id() {
        return send_id;
    }

    public void setSend_id(String send_id) {
        this.send_id = send_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }


}
