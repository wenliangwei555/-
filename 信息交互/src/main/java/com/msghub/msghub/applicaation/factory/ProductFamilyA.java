package com.msghub.msghub.applicaation.factory;

/**
 * 1号产品组 a产品
 *
 * @Author 温莨
 * @Date 2020/1/31 16:08
 * @Version 1.0
 */
public class ProductFamilyA extends AbstractProductA {
    @Override
    public void get() {
        System.out.println("我是一号产品的 a产品");
    }
}
