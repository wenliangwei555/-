package com.msghub.msghub.applicaation.factory;

/**
 * @Author 温莨
 * @Date 2020/1/31 16:19
 * @Version 1.0
 */
public class Factory2 implements ProductFactory {

    @Override
    public Product getA() {
        return new ProductFamilyA2();
    }

    @Override
    public Product getB() {
        return null;
    }
}
