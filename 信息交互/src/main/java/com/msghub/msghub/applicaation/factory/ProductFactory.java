package com.msghub.msghub.applicaation.factory;

/**
 * 抽象工厂
 *
 * @Author 温莨
 * @Date 2020/1/31 16:16
 * @Version 1.0
 */
public interface ProductFactory {
    /**
     * 获取a
     *
     * @return
     */
    Product getA();

    /**
     * 获取b
     *
     * @return
     */
    Product getB();

}
