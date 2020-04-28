package com.msghub.msghub.test.colne;

import java.io.Serializable;

/**
 * @Author 温莨
 * @Date 2020/2/11 11:34
 * @Version 1.0
 */
public class Qwe  implements Cloneable,Serializable{

    private static final long serialVersionUID = -6468116433312928055L;

    /**
     * 浅克隆
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private String  name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Qwe{" +
                "name='" + name + '\'' +
                '}';
    }
}
