package com.msghub.msghub.test;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

import java.util.Properties;

/*/**
mybatis的自定义插件 拦截器
 */

public class Singleton implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }

    @Override
    public Object plugin(Object target) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private static final Singleton INSTANCE = new Singleton();
    public static Singleton getInstance() {
        return INSTANCE;
    }
   /* private    boolean   CAS（Variable V，int oldValue，int newValue） {
        if (V.getValue() == oldValue) {
            setValue(newValue);
        } else {
            return false;
        }
    }*/
}
