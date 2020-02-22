package com.msghub.msghub.strategy;

import com.msghub.msghub.model.Parameter;
import com.msghub.msghub.model.Result;
import org.springframework.stereotype.Component;

/**
 * 切换数据源使用   注解方式   (注解的好处 业务清晰   mapper对应一个xml  或者对应一个具体的方法
 *
 * @bean注入一个sql 工厂  添加连接池
 * <p>
 * 为什么要用aop  不更改业务代码情况加对方法的加功能呢 把系统服务和业务代码分离出来
 * 上下文
 */
@Component
public  final  class Context {

    private IStrategy mStrategy;
    //volatile是为了不让分配内存指令、
    private volatile static Context context = null;

    public IStrategy getmStrategy() {
        return mStrategy;
    }

    public void setmStrategy(IStrategy mStrategy) {
        this.mStrategy = mStrategy;
    }

    /**
     * @param strategy  发送类型
     * @param parameter 发送参数
     */
    public static Result algorithm(IStrategy strategy, Parameter parameter) {
        if (context == null) {
            synchronized (Context.class) {//**对象初始化指令、
                if (context == null) {
                    context = new Context();
                }
            }
        }
        context.setmStrategy(strategy);
        return context.mStrategy.Sendmsg(parameter);
    }
}

