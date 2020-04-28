package com.msghub.msghub.strategy;

import com.msghub.msghub.config.CheckResult;
import com.msghub.msghub.config.Config;
import com.msghub.msghub.exception.AppException;
import com.msghub.msghub.model.Parameter;
import com.msghub.msghub.model.Result;
import com.sun.mail.smtp.SMTPAddressFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.msghub.msghub.service.MailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

/**
 * 邮件
 */
@Component
public class Mail extends IStrategy {


    private static MailService mailService;

    /**
     *
     */
    public Mail() {
        super.setChannel(Config.MAIL);
    }

    /**
     * @param mailService
     */
    @Autowired
    public Mail(MailService mailService) {
        Mail.mailService = mailService;
    }


    /**
     * 错误状态码
     * 3500 :公司id不合法
     * 3501 :模板id不合法
     * 3502 :模板变量不合法
     * 3503 :接收人不合法
     * 3000 :发送成功
     * 3001 :参数不完整
     *
     * @param parameter
     * @return
     */
    @Override
    public Result Sendmsg(Parameter parameter) {
        Result setResult = new Result();
        try {
            //邮箱发送需要传入的参数
            String toEmail = "";        // 收件人		Parameter received[] 收件人数组
            String fromEmail = "";    // 发件人		msg_config send_id
            String authEmail = "";    // 授权邮箱	msg_config send_id
            String authPaw = "";        // 授权码		msg_config assword
            String title = "";        // 邮件标题	template text &前是标题
            String text = "";            // 邮件内容	template text &后是内容
            // String[] company = {fromEmail, authEmail, authPaw};
            String[] company = mailService.getcompany(parameter.getClient_id());
            String template = mailService.getText(parameter.getTemplate_id());
            if (template == null) {
                return template(setResult);
            }
            String[] split = template.split("&");//拆
            title = split[0];//标题
            text = split[1];//内容
            Map<Object, Object> map = parameter.getMap();
            for (Map.Entry<Object, Object> enmap : map.entrySet()) {
                String stkey = (String) enmap.getKey();
                String stvalue = (String) enmap.getValue();
                if ("".equals(stvalue.trim())) {
                    return variablenull(setResult);
                }
                text = text.replace(stkey, stvalue);
            }
            String[] received = parameter.getReceived();
            for (String str : received) {
                if ("".equals(str.trim())) {
                    return recipientnull(setResult);
                }
                toEmail = str;
                if (toEmail.matches(Config.REGEX6)) {
                    setResult = sendEmail(toEmail, company[0], company[1], company[2], title, text, setResult);
                } else {
                    return recipient(setResult);//错误}
                }
            }
            return setResult;
        } catch (Exception e) {
            return messaging(setResult);
        }
        //return succeed(setResult, 0);
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
     * 变量空
     *
     * @return
     */
    private Result variablenull(Result setResult) {
        setResult.setText(CheckResult.RECIPIENTNULL.getMsg());
        setResult.setException(new AppException("I10011"));  //地址错误
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

    /**
     * @param setResult
     * @return
     */
    private Result messaging(Result setResult) {
        setResult.setText(CheckResult.MESSAGING.getMsg());
        setResult.setException(new AppException("I10007"));  //地址错误
        return setResult;
    }


    /**
     * 发送QQ邮箱的方法
     *
     * @param toEmail
     * @param fromEmail
     * @param authEmail
     * @param authPaw
     * @param title
     * @param text
     */
    public Result sendEmail(String toEmail, String fromEmail, final String authEmail, final String authPaw,
                            String title, String text, Result setResult) {
        // 收件人电子邮箱
        String to = toEmail;
        // 发件人电子邮箱
        String from = fromEmail;
        // 指定发送邮件的主机为 localhost
        String host = "smtp.qq.com";  //QQ 邮件服务器
        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        // 获取默认session对象
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(authEmail, authPaw); //发件人邮件用户名、授权码
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));
            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: 头部头字段
            //message.setSubject("I love u");
            message.setSubject(title);
            // 设置消息体
            message.setContent(text, "text/html;charset=UTF-8");
            // 发送 HTML 消息, 可以插入html标签
            // 发送消息
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
            System.out.println("失败");
            return recipient(setResult); //失败
        }
        return succeed(setResult);//成功
    }

    /**
     * 失败
     *
     * @param setResult
     * @return
     */
    private Result recipient(Result setResult) {
        setResult.setText(CheckResult.MESSAGING.getMsg());
        setResult.setException(new AppException("I10013"));
        return setResult;
    }

    /**
     * 成功
     *
     * @param setResult
     * @return
     */
    public Result succeed(Result setResult) {
        setResult.setText(CheckResult.SUCCEED.getMsg());
        setResult.setCode(0);
        return setResult;
    }
}
