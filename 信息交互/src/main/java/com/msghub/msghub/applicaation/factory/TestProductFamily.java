package com.msghub.msghub.applicaation.factory;

/**
 * @Author 温莨
 * @Date 2020/1/31 16:20
 * @Version 1.0
 */
public class TestProductFamily {
    /**
     *抽象工厂模式     
     * @param args
     */
    public static void main(String[] args) {
        //创建工厂对的实例
        ProductFactory factory1 = new Factory1();
        ProductFactory factory2 = new Factory2();
        //调用工厂的获取方法
        factory1.getA().get();
        factory1.getB().get();


        factory2.getA().get();
    }
}
