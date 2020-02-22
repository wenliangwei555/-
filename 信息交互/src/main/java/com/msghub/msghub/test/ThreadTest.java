package com.msghub.msghub.test;

import com.msghub.msghub.config.Config;

import javax.xml.transform.Source;

public  class ThreadTest implements Runnable ,Cloneable {
    @Override
    public  void run() {
        while (Config.count <= 10) {
            synchronized (ThreadTest.class) {
                if (Config.count %2==0) {
                    System.out.println(Thread.currentThread().getName() +  ++Config.count+" ------------" + System.currentTimeMillis());
                    ThreadTest.class.notify();
                }else {
                    try {
                        ThreadTest.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
           }
        }
    }
}

