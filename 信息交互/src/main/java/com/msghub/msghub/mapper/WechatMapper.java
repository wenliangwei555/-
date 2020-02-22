package com.msghub.msghub.mapper;

import com.msghub.msghub.model.Msgconfig;
import com.msghub.msghub.model.Template;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 微信的连接层
 */
@Mapper
@EnableScheduling
public interface WechatMapper {
    /**
     * 查询opid的方法
     */
    String findByOpenId(String str);

    /**
     * 查询账号
     */
    Msgconfig findBySendid(Msgconfig client_id);

    Template getTemplate(String trim);
}
