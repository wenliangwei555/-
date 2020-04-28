package com.msghub.msghub.config.wechatconfig;


import com.msghub.msghub.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @Component di
 * <p>
 * @EnableScheduling 开启定时器AccessTokenTask将放进access_token内存，并设置延迟一秒执行，每7000秒调用一次
 *  * @Author 温莨
 * @Date 2020/1/31 16:02
 * @Version 1.0
 */
//@Component
//@EnableScheduling
public class AccessTokenTask {



    @Autowired
    private GetToken getToken;
    @Autowired
    private Parameterapp parameter;

    /**
     * 0点第一次  获取   0.55
     * <p>
     * ccess_token是公众号的全局唯一接口调用凭据，
     * 公众号调用各接口时都需使用access_token。开发者需要进行妥善保存。
     * access_token的存储至少要保留512个字符空间。access_token的有效期目前为2个小时，
     * 需定时刷新，重复获取将导致上次获取的access_token失效。
     * <p>
     * 公众平台的API调用所需的access_token的使用及生成方式说明：
     * <p>
     * 1、建议公众号开发者使用中控服务器统一获取和刷新access_token，
     * 其他业务逻辑服务器所使用的access_token均来自于该中控服务器，
     * 不应该各自去刷新，否则容易造成冲突，导致access_token覆盖而影响业务；
     * <p>
     * 2、目前access_token的有效期通过返回的expire_in来传达，目前是7200秒之内的值。
     * 中控服务器需要根据这个有效时间提前去刷新新access_token。在刷新过程中，
     * 中控服务器可对外继续输出的老access_token，此时公众平台后台会保证在5分钟内，
     * 新老access_token都可用，这保证了第三方业务的平滑过渡；
     * <p>
     * 3、access_token的有效时间可能会在未来有调整，所以中控服务器不仅需要内部定时主动刷新，
     * 还需要提供被动刷新access_token的接口，这样便于业务服务器在API调用获知access_token已超时的情况下，
     * 可以触发access_token的刷新流程。
     * <p>
     * 公众号和小程序均可以使用AppID和AppSecret调用本接口来获取access_token。AppID和AppSecret可在
     * “微信公众平台-开发-基本配置”页中获得（需要已经成为开发者，且帐号没有异常状态）。**调用接口时，
     * 请登录“微信公众平台-开发-基本配置”提前将服务器IP地址添加到IP白名单中，点击查看设置方法，
     * 否则将无法调用成功。**小程序无需配置IP白名单。
     * access_token 是小程序的全局唯一调用凭据
     * <p>
     * access_token 的有效期为 2 个小时，需要定时刷新 access_token，重复获取会导致之前一次获取的
     * <p>
     * access_token 失效
     * <p>
     * 延迟一秒执行
     * <p>
     */
    //@Scheduled(initialDelay = 1000, fixedDelay = 7000 * 1000)
    public void getTouTiaoAccessToken() {
        try {
            String token = getToken.getToken(parameter.getAppid(), parameter.getAppsecrect()).getAccesstoken();
            //将获取到的token放到内存
            WeixinUrl.access_token = token;
            System.out.println("获取 access_token 时间--" + DateUtil.getNow("yyyy-MM-dd HH:mm:ss") + WeixinUrl.access_token);
        } catch (Exception e) {
            System.out.println("logger.error(\"获取微信adcessToken出错，信息如下\");");
            e.printStackTrace();
        }
    }

}
