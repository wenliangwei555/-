package com.msghub.msghub.service.impl;

import com.msghub.msghub.mapper.WechatMapper;
import com.msghub.msghub.model.Msgconfig;
import com.msghub.msghub.model.Template;
import com.msghub.msghub.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信的service实现层
 */
@Service
public class WeChatServiceImpl implements WechatService {



    public  WechatMapper wechatMapper = null;

    public WeChatServiceImpl() {
    }

    @Autowired
    public WeChatServiceImpl(WechatMapper wechatMapper) {
        this.wechatMapper = wechatMapper;
    }

    //查询openid
    @Override
    public String findByOpenId(String str) {
        return  wechatMapper.findByOpenId(str);

    }
//查询账号
    @Override
    public Msgconfig  findBySendid(String client_id, String cellphoneMsgType) {
        Msgconfig msgconfigs = new Msgconfig();
        msgconfigs.setClient_id(client_id);
        msgconfigs.setAffiliation(cellphoneMsgType);
        return  wechatMapper.findBySendid(msgconfigs);
    }

    @Override
    public Template getTemplate(String trim) {
        return wechatMapper.getTemplate(trim);
    }
}
