package com.msghub.msghub.model;

import java.io.Serializable;


/**
 * 序列化
 */
public class user implements Serializable ,Cloneable {
    private static final long serialVersionUID = 1L;


    /**
     * 对象克隆
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    int id;
    transient  String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
