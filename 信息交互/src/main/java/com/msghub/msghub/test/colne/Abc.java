package com.msghub.msghub.test.colne;

import java.io.Serializable;

/**
 * @Author 温莨
 * @Date 2020/2/11 11:34
 * @Version 1.0
 */
public class Abc implements Cloneable, Serializable {

    private static final long serialVersionUID = 6536790234017060938L;

    private Integer id;
    private Integer age;
    private Qwe qq;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    /**
     * 深克隆
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Abc Abc = (Abc) super.clone();
        Qwe clone = (Qwe) Abc.getQq().clone();
        //如果是多个引用数据类型非常繁琐  可以用序列化来完成深度克隆
        Abc.setQq(clone);
        /* return super.clone();*/
        return Abc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Qwe getQq() {
        return qq;
    }

    public void setQq(Qwe qq) {
        this.qq = qq;
    }

    @Override
    public String toString() {
        return "Abc{" +
                "id=" + id +
                ", qq=" + qq +
                '}';
    }
}
