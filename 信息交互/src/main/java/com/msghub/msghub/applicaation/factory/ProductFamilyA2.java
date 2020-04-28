package com.msghub.msghub.applicaation.factory;

/**
 * 2号产品的 a产品
 *
 * @Author 温莨
 * @Date 2020/1/31 16:14
 * @Version 1.0
 */
public class ProductFamilyA2 extends AbstractProductA {
    @Override
    public void get() {
        System.out.println("我是2号产品的 a产品");
    }
}