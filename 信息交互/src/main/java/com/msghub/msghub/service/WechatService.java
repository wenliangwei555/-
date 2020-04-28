package com.msghub.msghub.service;


import com.msghub.msghub.model.Msgconfig;
import com.msghub.msghub.model.Template;

public interface WechatService {
    //查询openid的方法
    String findByOpenId(String str);
    //查询账号密码
    Msgconfig  findBySendid(String client_id, String cellphoneMsgType);

    Template getTemplate(String trim);
}
