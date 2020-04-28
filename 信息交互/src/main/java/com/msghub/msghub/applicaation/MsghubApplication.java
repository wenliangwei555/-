package com.msghub.msghub.applicaation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * jvm的组件 类加载器  运行时数据区   执行引擎  本地库接口
 *
 * @Author 温莨
 * @Date 2020/1/31 16:18
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = "com.msghub")
@MapperScan("com.msghub.msghub.mapper")
public class MsghubApplication extends SpringBootServletInitializer {

    /**
     * 系统的介绍
     * 信息交互系统是基于 短信推送 邮箱推送 微信推送  极光推送  来实现的   架构中的其他业务来调用 交互系统
     * 2、为什么要有这个系统（）
     * 原来所有的功能都在一个项目里面 开发快  部署方便 测试也方便  后来业务越来越多 代码冗余的问题越来越严重
     * 经过团队的讨论 拆分出啦 产品层  渠道控制层 业务服务层  业务服务支撑层  基本数据层  其中的产品层 渠道控制层 业务服务层
     * 都涉及到 消息推送 比如异常信息的反馈  验证码一些东西 所以把他们抽取出来写啦个信息交互系统
     * 短信用的是阿里云短信平台  4分钱一条 到时候会在平台加一下单价  若果发短信会在交互系统中扣除对应的价钱
     * 推送的具体流程吗
     * 业务调用交互系统  根据发送类型 走不同的发送渠道 判断公司id是否开通业务 余额是否足够
     * 在根据模板id 找到模板 和模板的变量进行拼接  然后在调用第三房接口发送信息
     * 这个系统的数据库是怎么设计的呢？
     * 设计啦  模板表  模板控制表  业务开通表  字典表  余额表  通讯表
     * 6、这个系统能发送非模板消息吗e
     * 不能啊  只能发送根据模板id找到要发送的模板  模板都是设计好的
     * 7、在消息发送之前你们都需要做什么样的校验呢
     * 参数非空校验
     * 业务开通校验
     * 余额是否足够
     * 模板i是否能找到模板
     * 模板拼接有没有错误
     * 是否群发
     * 是否限制平台发送
     * * 8、为什么不需要对收件人地址做正则表达式校验呢
     * 在业务调用 交互系统前 会在前端对输入的接收人校验  在渠道控制层 也会对数据进行校验  第三方平台也会校验
     * 9、你们系统的异常是如何处理的呢？
     * 通过spring mvc的 异常处理器统一处理
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * 1、系统简介
     * 这个系统是基于极光推送  微信推送  邮箱推送  短信推送来完成的
     * 用于 架构中的 其他业务来调用
     * 2、为什么要有这个系统（存在的意义）
     * 原来一个项目  开发快 部署方便  测试也方便  后来业务越来越多
     * 代码冗余的问题越来越严重 经过项目组的讨论 拆分出 产品层 渠道控制层
     * 业务服务层  业务服务支撑层  基本数据层
     * 其中的产品层 渠道控制 和业务服务层  会用到信息推送 比如异常反馈  验证码
     * 所以根据推送功能写出这个信息交互系统
     * <p>
     * 3、短信这部分的费用是怎么算的
     * 它有 5-6个开发商在用   在用到发短信的时候  会进行短信余额扣除
     * -- 你们用的是什么第三方短信平台？  2000 5W   4分钱一条    8分一条
     * 阿里云短信平台。
     * -- 不贵吗？
     * 4、能说一下微信推送的具体流程吗
     * 业务发送请求  到交互系统 判断发送的type类型走不同的方法 判单公司标识 来判断有没有和是否开通业务
     * 在判断传入的接收人用户id和模板id是否存在
     * 来找到对应的模板和oppenid 使用String apl来进行模板拼接然后调用微信平台的接口来发送
     * <p>
     * 5、这个系统的数据库是怎么设计的呢？
     * 设计啦  模板表  余额表  通讯表  业务开通表   模板控制表  字典表
     * 6、这个系统能发送非模板消息吗e
     * 不能的  模板信息都是设计定的
     * 7、在消息发送之前你们都需要做什么样的校验呢
     * 非空参数校验
     * 判断发送公司是否开通业务
     * 发送公司的余额是否足够
     * 模板id正否正确
     * 模板的变量是否匹配
     * 是否群发
     * 是否限制平台方发送
     * 8、为什么不需要对收件人地址做正则表达式校验呢
     * 因为 接收人的数据在业务发送的时候已经在前端校验过啦
     * 在渠道控制层中也会对数据做校验控制  第三方平台也会做校验的
     * 9、你们系统的异常是如何处理的呢？
     * 通过mvc的异常处理器 捕获转发到错误提示页面
     * 10、都有哪些异常码呢？
     * <p>
     * 11、当你主体业务接受到了异常码后你是如何处理的呢？
     * 渠道控制层 mvc的异常处理器捕获到 异常 判断不同的异常提示不同的错误提示页面
     * 如果是系统内部错误需要再次调用短信发送来提示开发人员 添加日志
     * 12、项目一共开发了多久？几个人开发的？
     * 1一个人 2个月
     * 13、能说明一下你的系统的执行流程吗？
     * 14、openID和Userid绑定的操作是在哪个系统的呢？
     * 在微信公众号系统
     * 15、接口参数的是怎么设计的呢？为什么
     *
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MsghubApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MsghubApplication.class, args);
    }

}
