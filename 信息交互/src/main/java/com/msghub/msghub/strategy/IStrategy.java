package com.msghub.msghub.strategy;

import com.msghub.msghub.model.Parameter;
import com.msghub.msghub.model.Result;
import org.springframework.stereotype.Component;

/**
 * 信息发送
 */
@Component
public abstract class IStrategy {

    //发送类型    channel 参数 (1 短信 2微信 3 邮箱)
    public String channel;

    //发送方法
    abstract Result Sendmsg(Parameter parameter);

    private Result test() {
        return null;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
