package com.msghub.msghub.model;

import com.msghub.msghub.strategy.IStrategy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 参数类
 */
@Component
public class Parameter {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String client_id;

    private String template_id;

    private Map<Object, Object> map=new HashMap<>();

    private String[] received;

    private IStrategy lstrategy;

    public IStrategy getLstrategy() {
        return lstrategy;
    }

    public void setLstrategy(IStrategy lstrategy) {
        this.lstrategy = lstrategy;
    }


    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String[] getReceived() {
        return received;
    }

    public void setReceived(String[] received) {
        this.received = received;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "id='" + id + '\'' +
                ", client_id='" + client_id + '\'' +
                ", template_id='" + template_id + '\'' +
                ", map=" + map +
                ", received=" + Arrays.toString(received) +
                ", lstrategy=" + lstrategy +
                '}';
    }
}
