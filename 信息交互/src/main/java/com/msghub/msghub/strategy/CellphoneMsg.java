package com.msghub.msghub.strategy;

import com.msghub.msghub.config.CheckResult;
import com.msghub.msghub.config.Config;
import com.msghub.msghub.exception.AppException;
import com.msghub.msghub.model.*;
import com.msghub.msghub.strategy.thirdparty.ConfigManager;
import com.msghub.msghub.strategy.thirdparty.Test;
import com.msghub.msghub.service.CellphoneMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 短信发送策略类
 */
@SuppressWarnings("unused")
@Component
public class CellphoneMsg extends IStrategy {
    //注入
    private  static  CellphoneMsgService cellphoneMsgService = null;

    /**
     * 添加标识发送标识
     */
    public CellphoneMsg() {
        super.setChannel(Config.cellphone);
    }

    /**
     * 构造注入
     *
     * @param cellphoneMsgService 构造注入
     */
    @Autowired(required = true)
    public CellphoneMsg(CellphoneMsgService cellphoneMsgService) {
        CellphoneMsg.cellphoneMsgService = cellphoneMsgService;
    }

    /**
     * 908722902
     * guo151000...
     * <p>
     * 返回错误码
     * 100001：参数传入为空
     * 100004：服务未开通
     * 100005：余额不足
     * 100006; 模板变量错误
     * -100001	鉴权不通过,请检查账号,密码,时间戳,固定串,以及MD5算法是否按照文档要求进行设置
     * -100002	用户多次鉴权不通过,请检查帐号,密码,时间戳,固定串,以及MD5算法是否按照文档要求进行设置
     * -100003	用户欠费
     * -100004	custid或者exdata字段填写不合法
     * -100011	短信内容超长
     * -100012	手机号码不合法
     * -100014	手机号码超过最大支持数量（1000）
     * -100029	端口绑定失败
     * -100056	用户账号登录的连接数超限
     * -100057	用户账号登录的IP错误
     * -100999	平台数据库内部错误
     *
     * @param parameter
     * @return 返回发送信息状态码
     */
    @Override
    public Result Sendmsg(Parameter parameter) {
        System.out.println(cellphoneMsgService.get1());
        Result setResult = new Result();
        if (Channel_status(parameter)) {//效验服务是否开通  或者未传入公司id
            return through(setResult);
        }
        if (isMoney(parameter)) {//效验是否余额是否足够 或者未传入公司id
            return scarceMoney(setResult);
        }
        //获取该公司账号密码
        Msgconfig msgconfig = queryMsgconfig(parameter, Config.cellphone);
        //拼接该公司的模板
        String text = Splicing(parameter);
        if ("".equals(text) || text == null) {
            return template(setResult);
        }
        //获取手机号
        String[] received = parameter.getReceived();
        //发送参数  账号密码  发送内容  是否群发
        Integer integer = null;
        try {
            integer = sendOut(msgconfig, text, received, parameter);
        } catch (Exception e) {
            return template(setResult);
        }
        if (integer == 0) {
            charge(parameter, parameter.getClient_id(), received.length);
            return succeed(setResult, integer);
        }
        return internal(setResult, integer);
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


    /**
     * @param setResult
     * @return
     */
    private Result scarceMoney(Result setResult) {
        setResult.setText(CheckResult.SCARCEMONEY.getMsg());
        setResult.setException(new AppException("I10004"));  //余额不够
        return setResult;
    }


    /**
     * 未开通
     *
     * @param setResult
     * @return
     */
    private Result through(Result setResult) {
        setResult.setText(CheckResult.BUSINESS.getMsg());
        setResult.setException(new AppException("I10003"));  //业务未开通
        return setResult;
    }


    /**
     * 发送成功  计算收费
     *
     * @param client_id 公司Id
     * @param received  发送的条数
     */
    private void charge(Parameter parameter, String client_id, Integer received) {
        Bill bill = getBill(parameter);
        //减发送条数
        bill.setNumber(bill.getNumber() - received);
        //减余额
        bill.setMoney(bill.getMoney() - bill.getSet_meal_type() * received);
        cellphoneMsgService.charge(bill);

    }

    /**
     * 发送方法
     *
     * @param msgconfig 配置账号密码
     * @param text      模板内容
     * @param received  接收人的数组
     * @param parameter 参数对象
     */
    private Integer sendOut(Msgconfig msgconfig, String text, String[] received, Parameter parameter) {
        String masterIpAddress = "api02.monyun.cn:7901/";
        //备IP1  选填  2
        String ipAddress1 = "api01.monyun.cn:7901/";
        //备IP2  选填
        ConfigManager.setIpInfo(masterIpAddress, ipAddress1, null, null);
        //设置IP

        if (parameter.getReceived().length > 1) {//接收人大于1是群发  否则单发
            return Test.batchSend(msgconfig.getSend_id(), msgconfig.getPassword(), text, true, groupSplicing(received));
        }
        return Test.singleSend(msgconfig.getSend_id(), msgconfig.getPassword(), text, true, personalSplicing(received));
    }

    /**
     * 单发
     * 转换字符
     *
     * @param received 手机号数组
     * @return
     */
    private String personalSplicing(String[] received) {
        String Group = "";
        for (String fileName : received) {
            Group += fileName;
        }
        return Group;
    }

    /**
     * 群发
     * 加工手机号
     *
     * @param received 手机号数组
     * @return
     */
    private String groupSplicing(String[] received) {
        String Group = "";
        for (String fileName : received) {
            Group += fileName + ",";
        }
        Group = Group.substring(0, Group.length() - 1); //对Group字符串最后一位的','进行切割掉
        return Group;
    }

    /**
     * 模板拼接
     *
     * @param parameter 参数对象
     * @return
     */
    private String Splicing(Parameter parameter) {
        Map<Object, Object> map = parameter.getMap();
        Template template = cellphoneMsgService.queryTemplate(parameter.getTemplate_id());
        if (template == null) {
            return "";
        }
        String text = template.getText();
        List<String> list = Config.getList();
        for (String fileName : list) {
            String s = (String) map.get(fileName);
            if (s == null || s.toCharArray().length > 6 || "".equals(s.trim())) {
                return "";
            }
            text = text.replaceFirst(fileName, s);
        }
        return text;
    }

    /**
     * 变量空
     *
     * @return
     */
    private Result variablenull() {
        Result setResult = new Result();
        setResult.setText(CheckResult.RECIPIENTNULL.getMsg());
        setResult.setException(new AppException("I10011"));
        return setResult;
    }

    /**
     * 查询公司的账号密码
     *
     * @param parameter 参数对象
     * @param
     * @return
     */
    public Msgconfig queryMsgconfig(Parameter parameter, String cellphoneMsgType) {
        Msgconfig msgconfigs = new Msgconfig();
        msgconfigs.setClient_id(parameter.getClient_id());
        msgconfigs.setAffiliation(cellphoneMsgType);
        return cellphoneMsgService.queryCompany(msgconfigs);
    }

    /**
     * 获取短信剩余条数
     *
     * @param parameter 参数对象
     * @return
     */
    private Bill getBill(Parameter parameter) {
        return cellphoneMsgService.queryisMoney(parameter.getClient_id());
    }


    /**
     * 效验公司短信余额是否小于
     *
     * @param parameter 参数对象
     * @return
     */
    private boolean isMoney(Parameter parameter) {
        Bill ismoney = null;
        try {
            ismoney = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        ismoney = getBill(parameter);
        if (0 >= ismoney.getMoney()) {//0大于等于余额  代表余额不足
            return true;  //提示钱不足
        }
        return false;
    }

    /**
     * 效验有没有开通的方法
     *
     * @param parameter 参数对象
     * @return
     */
    private boolean Channel_status(Parameter parameter) {
        Business business = cellphoneMsgService.queryBusiness(parameter.getClient_id());
        if (business != null) {
            if ("1".equals(business.getChannel_status())) {
                return true;  //如果状态==1  是未开通
            }
            return false;  //找到啦记录并且不等于1
        }
        return true; ///找不到该公司的记录 返回未开通
    }

}
