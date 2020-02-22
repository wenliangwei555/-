package com.msghub.msghub.strategy;

import com.msghub.msghub.config.CheckResult;
import com.msghub.msghub.config.Config;
import com.msghub.msghub.exception.AppException;
import com.msghub.msghub.model.Msgconfig;
import com.msghub.msghub.model.Result;
import com.msghub.msghub.model.Template;
import com.msghub.msghub.service.WechatService;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import com.msghub.msghub.model.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
import java.util.Set;

/**
 * 微信的接受对象
 * <p>
 * 100001：参数不完整
 */
@Component
public class WeChat extends IStrategy {


    public static WechatService wechatService = null;

    public WeChat() {
        super.setChannel(Config.WECHAT);
    }

    @Autowired
    public WeChat(WechatService wechatService) {
        WeChat.wechatService = wechatService;
    }


    @Override
    public Result Sendmsg(Parameter parameter) {
        Result setResult = new Result();
        String client_id = parameter.getClient_id();
        //获取联系人的数组 获取数组中的userId 查询对应的openid
        String[] received = parameter.getReceived();
        String template_id = parameter.getTemplate_id();//获取对应的模板id
        Template template = wechatService.getTemplate(template_id.trim());
        if (template == null) {
            return template(setResult);
        }
        Map<Object, Object> map = parameter.getMap();//获取map
        String receive = null;
        Integer push = null;
        //遍历数组
        if (received == null || received.length == 0) {
            return recipientnull(setResult);
        }
        for (String str : received) {
            //查询接收人的openid
            receive = wechatService.findByOpenId(str.trim());
            CellphoneMsg CellphoneMsg = new CellphoneMsg();
            //查询微信的账号密码
            Msgconfig msgconfig = wechatService.findBySendid(parameter.getClient_id(), Config.WECHAT);
            if (msgconfig == null) {
                return gongsinull(setResult);
            }
            push = push(msgconfig.getSend_id(), msgconfig.getPassword(), receive, template_id, map);
        }
        if (push == 0) {
            return succeed(setResult, push);
        }
        return internal(setResult, push);
    }


    /**
     * 模板 错误
     *
     * @param setResult
     * @return
     */
    private Result template(Result setResult) {
        setResult.setText(CheckResult.TEMPLATEEROR.getMsg());
        setResult.setException(new AppException("I100012"));
        return setResult;
    }

    /**
     * 接收人空
     *
     * @return
     */
    private Result recipientnull(Result setResult) {
        setResult.setText(CheckResult.VARIABLENULL.getMsg());
        setResult.setException(new AppException("I10009"));  //地址错误
        return setResult;
    }

    /**
     * 模板 错误
     *
     * @param setResult
     * @return
     */
    private Result gongsinull(Result setResult) {
        setResult.setText(CheckResult.GONGSINULL.getMsg());
        setResult.setException(new AppException("I100022"));
        return setResult;
    }

    /**
     * 内部错误
     *
     * @param setResult
     * @return
     */
    private Result internal(Result setResult, Integer integer) {
        setResult.setCode(integer);
        setResult.setText(CheckResult.INTERIOR.getMsg());
        setResult.setException(new AppException("I10006"));  //内部错误
        return setResult;
    }

    /**
     * 成功
     *
     * @param setResult
     * @return
     */
    public Result succeed(Result setResult, Integer integer) {
        setResult.setText(CheckResult.SUCCEED.getMsg());
        setResult.setCode(integer);
        return setResult;
    }

    /*
     * 微信测试账号推送
     *
     * 单发
     */
    @GetMapping("/push")
    public static Integer push(String send_id, String password, String receive, String tem, Map<Object, Object> map) {
        // 1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(send_id);//微信公众号
        wxStorage.setSecret(password);//微信公众号的密码
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
     //   System.out.println(WeixinUrl.token);
        // 2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder().toUser(receive)// 要推送的用户openid
                .templateId(tem)// 模版id
                .url("https://www.baidu.com/")// 点击模版消息要访问的网址
                .build();
        // 3,如果是正式版发送模版消息，这里需要配置你的信息
        //把map变成set集合
        Set<Object> keyset = map.keySet();
        //遍历map集合
        for (Object key : keyset) {
            templateMessage.addData(new WxMpTemplateData(String.valueOf(key), String.valueOf(map.get(key)), "#FF00FF"));
        }
        // 发起推送
        try {
            String msg = wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            return 0;
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            return 1;
        }
    }
}
