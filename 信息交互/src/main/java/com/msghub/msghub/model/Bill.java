package com.msghub.msghub.model;

import sun.plugin2.message.Serializer;

import java.io.*;

/**
 * 账单类
 */
public class Bill implements Externalizable {

    /**
     * 序列化
     * @param out
     * @throws IOException
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
    }

    /**
     * 反序列化
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Object o = in.readObject();
    }

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    //id
    private String id;
    //公司标示
    private String client_id;
    //剩余金额
    private double money;
    //短信数量
    private Integer number;
    //套餐种类
    private double set_meal_type;
    //最好操作时间
    private String time;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public double getSet_meal_type() {
        return set_meal_type;
    }

    public void setSet_meal_type(double set_meal_type) {
        this.set_meal_type = set_meal_type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", money='" + money + '\'' +
                ", number=" + number +
                ", set_meal_type='" + set_meal_type + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
