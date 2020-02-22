package com.msghub.msghub.test;

import com.msghub.msghub.config.Config;


public class ThreadTest2 implements Runnable {


    /**
     * 线程的状态
     * 新建  可以运行   等待队列  唤醒运行
     *
     */
    @Override
    public void run() {
        while (Config.count <=10) {
            synchronized (ThreadTest.class) {
                if (Config.count %2==1) {
                    System.out.println(Thread.currentThread().getName() + " " + ++Config.count+"----------"+ System.currentTimeMillis());
                    ThreadTest.class.notify();//唤醒一个线程
                }else {
                    try {
                        ThreadTest.class.wait();//让线程等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
           }
        }
    }
}

