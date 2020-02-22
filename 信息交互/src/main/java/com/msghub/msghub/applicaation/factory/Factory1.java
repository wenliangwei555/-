package com.msghub.msghub.applicaation.factory;

/**
 * @Author 温莨
 * @Date 2020/1/31 16:18
 * @Version 1.0
 */
public class Factory1 implements ProductFactory {
    @Override
    public Product getA() {
        return new ProductFamilyA();
    }

    @Override
    public Product getB() {
        return new ProductFamilyB();
    }
}
