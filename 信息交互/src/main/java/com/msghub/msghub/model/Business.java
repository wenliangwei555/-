package com.msghub.msghub.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 开通业务员
 */
@Component
public class Business {
    @Value("100")
    private Integer bid; //主键
    private String channel;//交互渠道 发送类型 1短信
    private Integer client_id;//公司标示
    private String channel_status;//业务状态 (0开通 1未开通)

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public String getChannel_status() {
        return channel_status;
    }

    public void setChannel_status(String channel_status) {
        this.channel_status = channel_status;
    }

    @Override
    public String toString() {
        return "Business{" +
                "bid=" + bid +
                ", channel='" + channel + '\'' +
                ", client_id=" + client_id +
                ", channel_status='" + channel_status + '\'' +
                '}';
    }
}
