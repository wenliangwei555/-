package com.msghub.msghub.strategy.thirdparty;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


/**
 * @功能概要：发送管理类
 * @公司名称： ShenZhen Montnets Technology CO.,LTD.
 */
public class CHttpPost {


    // http请求失败
    public static int ERROR_310099 = -310099;

    // 请求超时时间(毫秒) 5秒
    public static int HTTP_REQUEST_TIMEOUT = 5 * 1000;

    // 响应超时时间(毫秒) 60秒
    public static int HTTP_RESPONSE_TIMEOUT = 60 * 1000;

    /**
     * @param message 短信参数对象
     * @param msgId   返回值为0，则msgId有值。返回值非0，则msgId为空的字符串。字符串为"手机号码,custId,网关流水号"
     * @return 0:成功;非0:返回错误代码
     * @description 单条发送接口
     */
    public int singleSend(Message message, StringBuffer msgId) {
        // 定义返回值，默认单条发送失败
        int returnInt = ERROR_310099;
        try {// 去掉前后空格,对短信内容进行编码 urlencode（GBK明文）
            message.setContent(URLEncoder.encode(message.getContent(), "GBK"));
            String Message = null;
            StringBuffer messageBuffer = new StringBuffer("");
            // 单条发送
            System.out.println(message + "message");
            System.out.println(messageBuffer + "messageBuffer");
            returnInt = sendSmsByNotKeepAlive("single_send", message, messageBuffer);
            // returnInt为0,代表提交成功;returnInt不为0，代表提交失败
            if (returnInt == 0) {// 提交成功
                Message = messageBuffer.toString();
                // 请求成功后的处理
                Long rMsgid = null;
                String rCustid = "";
                // 处理返回结果
                if (Message != null && !"".equals(Message.trim())) {//解析XML
                    Document doc = DocumentHelper.parseText(Message);
                    Element mtrsp = doc.getRootElement();
                    // 获取是否成功标识
                    Element result = mtrsp.element("result");
                    returnInt = Integer.parseInt(result.getText());
                    // returnInt为0，则代表提交成功
                    if (returnInt == 0) {// 平台返回流水号
                        rMsgid = Long.parseLong(mtrsp.element("msgid").getText());
                        // 平台返回的custid
                        rCustid = mtrsp.element("custid").getText();
                        msgId.append(message.getMobile() + "," + rCustid + "," + rMsgid);
                    }
                }
            } else {
                // 提交失败，返回错误码
                return returnInt;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // returnInt不为0，则代表提交失败。否则，提交成功。
            if (returnInt != 0) {
                returnInt = ERROR_310099;
            }
        }
        return returnInt;
    }

    /**
     * @param message 参数对象
     * @param msgId   返回值为0，则msgId有值。返回值非0，则msgId为空的字符串。字符串为"手机号码,custId,网关流水号"
     * @return 0:成功 非0:返回错误代码
     * @description 相同内容群发
     */
    public int batchSend(Message message, StringBuffer msgId) {
        // 定义返回值，默认相同内容发送失败
        int returnInt = ERROR_310099;

        try {
            // 对短信内容进行编码 urlencode（GBK明文）
            message.setContent(URLEncoder.encode(message.getContent(), "GBK"));

            String Message = null;

            StringBuffer messageBuffer = new StringBuffer("");

            // 相同内容群发
            returnInt = sendSmsByNotKeepAlive("batch_send", message, messageBuffer);

            // returnInt为0,代表提交成功;returnInt不为0，代表提交失败
            if (returnInt == 0) {
                // 提交成功
                Message = messageBuffer.toString();
                Long rMsgid = null;
                String rCustid = "";
                // 处理返回结果
                if (Message != null && !"".equals(Message.trim())) {
                    //解析XML
                    Document doc = DocumentHelper.parseText(Message);
                    Element mtrsp = doc.getRootElement();
                    // 获取是否成功标识
                    Element result = mtrsp.element("result");
                    returnInt = Integer.parseInt(result.getText());
                    // returnInt为0，则代表提交成功
                    if (returnInt == 0) {
                        // 平台返回流水号
                        rMsgid = Long.parseLong(mtrsp.element("msgid").getText());
                        // 平台返回的custid
                        rCustid = mtrsp.element("custid").getText();
                        msgId.append(message.getMobile().split(",")[0] + "," + rCustid + "," + rMsgid);
                    }
                }
            } else {
                // 提交失败，返回错误码
                return returnInt;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // returnInt不为0，则代表提交失败。否则，提交成功。
            if (returnInt != 0) {
                returnInt = ERROR_310099;
            }
        }
        return returnInt;
    }

    /**
     * @param userId      用户账号
     * @param password    用户密码
     * @param timestamp   时间戳  密码加密时，才需要传值
     * @param multiMtList 个性化短信对象
     * @param msgId       返回值为0，则msgId有值。返回值非0，则msgId为空的字符串。字符串为"手机号码,custId,网关流水号"
     * @return 0:成功 非0:返回错误代码
     * @description 个性化群发
     */
    public int multiSend(String userId, String password, String timestamp, List<MultiMt> multiMtList, StringBuffer msgId) {
        // 定义返回值，默认个性化群发失败
        int returnInt = ERROR_310099;

        try {
            Message message = new Message();

            //时间戳不为空，则设置时间戳
            if (timestamp != null && !"".equals(timestamp.trim())) {
                message.setTimestamp(timestamp);
            }

            // 将 userid转成大写,以防大小写不一致
            userId = userId.toUpperCase();

            // 设置账号
            message.setUserid(userId);
            // 设置密码
            message.setPwd(password);

            for (int j = 0; j < multiMtList.size(); j++) {
                MultiMt multiMt = multiMtList.get(j);

                // 对短信内容进行编码 urlencode（GBK明文）
                multiMt.setContent(URLEncoder.encode(multiMt.getContent(), "GBK"));
            }
            // 设置个性化详情
            message.setMultimt(multiMtList);

            String Message = null;
            StringBuffer messageBuffer = new StringBuffer("");

            // 个性化群发
            returnInt = sendSmsByNotKeepAlive("multi_send", message, messageBuffer);

            // returnInt为0,代表提交成功;returnInt不为0，代表提交失败
            if (returnInt == 0) {
                // 提交成功
                Message = messageBuffer.toString();
                Long rMsgid = null;
                String rCustid = "";
                // 处理返回结果
                if (Message != null && !"".equals(Message.trim())) {
                    //解析XML
                    Document doc = DocumentHelper.parseText(Message);
                    Element mtrsp = doc.getRootElement();
                    // 获取是否成功标识
                    Element result = mtrsp.element("result");
                    returnInt = Integer.parseInt(result.getText());
                    // returnInt为0，则代表提交成功
                    if (returnInt == 0) {
                        // 平台返回流水号
                        rMsgid = Long.parseLong(mtrsp.element("msgid").getText());
                        // 平台返回的custid
                        rCustid = mtrsp.element("custid").getText();
                        msgId.append(multiMtList.get(0).getMobile() + "," + rCustid + "," + rMsgid);
                    }
                }
            } else {
                // 提交失败，返回错误码
                return returnInt;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // returnInt不为0，则代表提交失败。否则，提交成功。
            if (returnInt != 0) {
                returnInt = ERROR_310099;
            }
        }
        return returnInt;
    }

    /**
     * @param userId    用户账号
     * @param password  用户密码
     * @param timestamp 时间戳  密码加密时，才需要传值
     * @return 0或者正数:成功;负数:返回错误代码
     * @description 查询余额接口
     */
    public int getBalance(String userId, String password, String timestamp) {
        // 定义返回值，默认查询余额失败
        int resultBalance = ERROR_310099;
        try {
            Message message = new Message();

            //时间戳不为空，则设置时间戳
            if (timestamp != null && !"".equals(timestamp.trim())) {
                message.setTimestamp(timestamp);
            }

            // 将 userid转成大写,以防大小写不一致
            userId = userId.toUpperCase();

            //设置账号
            message.setUserid(userId);

            // 设置密码
            message.setPwd(password);

            // 网关返回值信息
            String Message = null;
            StringBuffer messageBuffer = new StringBuffer("");
            //调用方法查询余额
            resultBalance = getMoRptFeeByNotKeepAlive("get_balance", message, messageBuffer);

            // returnInt为0,代表提交成功;returnInt不为0，代表提交失败
            if (resultBalance == 0) {
                // 提交成功
                Message = messageBuffer.toString();
                // 解析json
                if (Message != null && !"".equals(Message.trim())) {
                    //解析XML
                    Document doc = DocumentHelper.parseText(Message);
                    Element mtrsp = doc.getRootElement();
                    // 获取是否成功标识
                    int result = Integer.parseInt(mtrsp.element("result").getText());
                    // result为0，则查询余额成功
                    if (result == 0) {
                        // 短信余额
                        resultBalance = Integer.parseInt(mtrsp.element("balance").getText());
                    } else {
                        // result不为0，则是错误码
                        // 将返回值设置为错误码
                        resultBalance = result;
                    }
                }

            } else {
                // 提交失败，返回错误码
                return resultBalance;
            }

        } catch (Exception e) {
            e.printStackTrace();
            // 出现异常，查询余额失败
            resultBalance = ERROR_310099;
        }
        return resultBalance;
    }

    /**
     * @param userId    用户账号
     * @param password  用户密码
     * @param timestamp 时间戳  密码加密时，才需要传值
     * @param retsize   每次请求想要获取的上行最大条数。
     * @param mos       返回的上行集合
     * @return 0:成功;非0:返回错误代码
     * @description 获取上行
     */
    public int getMo(String userId, String password, String timestamp, int retsize, List<MO> mos) {
        int returnInt = ERROR_310099;
        try {

            Message message = new Message();

            //时间戳不为空，则设置时间戳
            if (timestamp != null && !"".equals(timestamp.trim())) {
                message.setTimestamp(timestamp);
            }

            // 将 userid转成大写,以防大小写不一致
            userId = userId.toUpperCase();

            // 设置账号
            message.setUserid(userId);

            // 设置密码
            message.setPwd(password);

            // 设置最大获取retsize条上行
            message.setRetsize(retsize);

            String Message = null;
            StringBuffer messageBuffer = new StringBuffer("");
            //调用方法获取上行
            returnInt = getMoRptFeeByNotKeepAlive("get_mo", message, messageBuffer);

            // returnInt为0,代表提交成功;returnInt不为0，代表提交失败
            if (returnInt == 0) {
                // 提交成功
                Message = messageBuffer.toString();
                // 处理返回结果
                if (Message != null && !"".equals(Message.trim())) {
                    //解析XML
                    Document doc = DocumentHelper.parseText(Message);
                    Element mtrsp = doc.getRootElement();
                    // 获取是否成功标识
                    int result = Integer.parseInt(mtrsp.element("result").getText());

                    // result为0时，则获取上行成功
                    if (result == 0) {
                        Element mosElement = mtrsp.element("mos");
                        Iterator moIterator = mosElement.elementIterator("mo");
                        Element moElement = null;
                        MO mo = null;
                        //迭代mo集合
                        while (moIterator.hasNext()) {
                            moElement = (Element) moIterator.next();
                            mo = new MO();
                            //获取平台流水号
                            mo.setMsgid(Long.parseLong(moElement.element("msgid").getText()));
                            //手机号码
                            mo.setMobile(moElement.element("mobile").getText());
                            //通道号
                            mo.setSpno(moElement.element("spno").getText());
                            //扩展子号
                            mo.setExno(moElement.element("exno").getText());
                            //上行时间
                            mo.setRtime(moElement.element("rtime").getText());
                            //解码上行内容
                            mo.setContent(URLDecoder.decode(moElement.element("content").getText(), "UTF-8"));
                            //将mo添加到集合中
                            mos.add(mo);
                        }

                        // 获取上行成功
                        return 0;
                    } else {
                        // 获取上行失败
                        // 返回错误码
                        return result;
                    }
                } else {
                    // 获取上行失败
                    return returnInt;
                }
            } else {
                // 提交失败，返回错误码
                return returnInt;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 获取上行失败
            return ERROR_310099;
        }
    }

    /**
     * @param userId    用户账号
     * @param password  用户密码
     * @param timestamp 时间戳  密码加密时，才需要传值
     * @param retsize   每次请求想要获取的状态报告的最大条数。
     * @param rpts      返回状态报告集合
     * @return 0:成功;非0:返回错误代码
     * @description 状态报告
     */
    public int getRpt(String userId, String password, String timestamp, int retsize, List<RPT> rpts) {
        int returnInt = ERROR_310099;
        try {
            Message message = new Message();

            //时间戳不为空，则设置时间戳
            if (timestamp != null && !"".equals(timestamp.trim())) {
                message.setTimestamp(timestamp);
            }

            // 将 userid转成大写,以防大小写不一致
            userId = userId.toUpperCase();

            // 设置账号
            message.setUserid(userId);

            // 设置密码
            message.setPwd(password);

            // 设置最大获取retsize条状态报告
            message.setRetsize(retsize);

            String Message = null;
            StringBuffer messageBuffer = new StringBuffer("");
            //调用方法获取状态报告
            returnInt = getMoRptFeeByNotKeepAlive("get_rpt", message, messageBuffer);

            // returnInt为0,代表提交成功;returnInt不为0，代表提交失败
            if (returnInt == 0) {
                // 提交成功
                Message = messageBuffer.toString();
                // 处理返回结果
                if (Message != null && !"".equals(Message)) {
                    //解析XML
                    Document doc = DocumentHelper.parseText(Message);
                    Element mtrsp = doc.getRootElement();
                    // 获取是否成功标识
                    int result = Integer.parseInt(mtrsp.element("result").getText());
                    // result为0时，则获取状态报告成功
                    if (result == 0) {
                        Element rptsElement = mtrsp.element("rpts");
                        Iterator rptsIterator = rptsElement.elementIterator("rpt");
                        Element rptElement = null;
                        RPT rpt = null;
                        //迭代状态报告集合
                        while (rptsIterator.hasNext()) {
                            rptElement = (Element) rptsIterator.next();
                            rpt = new RPT();
                            // 消息编号
                            rpt.setMsgid(Long.parseLong(rptElement.element("msgid").getText()));
                            // 自定义流水号
                            rpt.setCustid(rptElement.element("custid").getText());
                            // 当前条数
                            rpt.setPknum(Integer.parseInt(rptElement.element("pknum").getText()));
                            // 总条数
                            rpt.setPktotal(Integer.parseInt(rptElement.element("pktotal").getText()));
                            // 手机号
                            rpt.setMobile(rptElement.element("mobile").getText());
                            // 通道号
                            rpt.setSpno(rptElement.element("spno").getText());
                            // 扩展号
                            rpt.setExno(rptElement.element("exno").getText());
                            // 状态报告对应的下行发送时间
                            rpt.setStime(rptElement.element("stime").getText());
                            // 状态报告返回时间
                            rpt.setRtime(rptElement.element("rtime").getText());
                            // 发送状态,0:成功 非0:失败
                            rpt.setStatus(Integer.parseInt(rptElement.element("status").getText()));
                            // 状态报告错误代码
                            rpt.setErrcode(rptElement.element("errcode").getText());
                            // 下行时填写的exdata
                            rpt.setExdata(rptElement.element("exdata").getText());
                            //状态报告错误代码的描述
                            rpt.setErrdesc(rptElement.element("errdesc").getText());
                            rpts.add(rpt);
                        }

                        // 获取状态报告成功
                        return 0;
                    } else {
                        // 获取状态报告失败
                        // 返回错误码
                        return result;
                    }
                } else {
                    // 获取状态报告失败
                    return ERROR_310099;
                }
            } else {
                // 提交失败，返回错误码
                return returnInt;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 获取状态报告失败
            return ERROR_310099;
        }
    }

    /**
     * @param methodName    方法名
     * @param message       短信请求实体类
     * @param messageBuffer 请求网关的返回值
     * @return 0:提交网关成功; 非0：提交网关失败,值为错误码
     * @description 短连接发送的方法
     */
    private int sendSmsByNotKeepAlive(String methodName, Message message, StringBuffer messageBuffer) {
        int failCode = ERROR_310099;
        try {
            //未设置IP
            if (!ConfigManager.ipIsSet) {
                return ERROR_310099;
            }

            //通过账号密码获取可用的IP
            String availableAddress = new CheckAddress().getAddressByUserID(message.getUserid(), message.getPwd(), message.getTimestamp());
            System.out.println(availableAddress + "availableAddress");
            //未获取到IP
            if (availableAddress == null || "".equals(availableAddress.trim())) {
                return failCode;
            }

            String requestHost = "http://" + availableAddress;
            String Message = null;
            Message = executeNotKeepAliveNotUrlEncodePost(message, requestHost + ConfigManager.REQUEST_PATH + methodName, methodName);
            System.out.println(Message);
            // Message为-310099,则请求失败，否则请求成功。短连接请求失败后，不重新请求。
            if (String.valueOf(ERROR_310099).equals(Message)) {
                //当前可用的IP和主IP相同,则说明主IP发送失败
                if (ConfigManager.masterIpAndPort.equals(availableAddress)) {
                    //主IP最近检测时间
                    ConfigManager.LAST_CHECK_TIME = Calendar.getInstance().getTimeInMillis();
                    //主IP状态更新为异常
                    ConfigManager.masterIPState = 1;
                }
                //可用IP设置为空
                ConfigManager.availableIpAndPort = null;

                // 返回错误码 单条发送失败
                return failCode;
            } else {
                // 请求成功
                messageBuffer.append(Message);
                // 请求成功，返回0
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 返回错误码 单条发送失败
            return failCode;
        }

    }

    /**
     * @param methodName    方法名
     * @param message       请求实体类
     * @param messageBuffer 请求网关的返回值
     * @return 0:提交网关成功; 非0：提交网关失败,值为错误码
     * @description 短连接获取上行、状态报告、余额方法
     */
    private int getMoRptFeeByNotKeepAlive(String methodName, Message message, StringBuffer messageBuffer) {
        int failCode = ERROR_310099;
        try {
            //未设置IP
            if (!ConfigManager.ipIsSet) {
                return ERROR_310099;
            }

            //通过账号密码获取可用的IP
            String availableAddress = new CheckAddress().getAddressByUserID(message.getUserid(), message.getPwd(), message.getTimestamp());

            //未获取到IP
            if (availableAddress == null || "".equals(availableAddress.trim())) {
                return failCode;
            }

            // 短连接获取余额
            String requestHost = "http://" + availableAddress;
            String Message = null;
            Message = executeNotKeepAliveNotUrlEncodePost(message, requestHost + ConfigManager.REQUEST_PATH + methodName, methodName);
            // Message为-310099,则请求查询余额接口失败，否则请求查询余额接口成功
            if (String.valueOf(ERROR_310099).equals(Message)) {
                //当前可用的IP和主IP相同,则说明主IP发送失败
                if (ConfigManager.masterIpAndPort.equals(availableAddress)) {
                    //主IP最近检测时间
                    ConfigManager.LAST_CHECK_TIME = Calendar.getInstance().getTimeInMillis();
                    //主IP状态更新为异常
                    ConfigManager.masterIPState = 1;
                }
                //可用IP设置为空
                ConfigManager.availableIpAndPort = null;

                // 返回错误码 查询余额失败
                return failCode;
            } else {
                // 请求成功
                messageBuffer.append(Message);
                // 请求成功，返回0
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failCode;
        }
    }

    /**
     * @param userid    用户账号
     * @param pwd       用户原始密码
     * @param timestamp 时间戳
     * @return 加密后的密码
     * @description 对密码进行加密
     */
    public String encryptPwd(String userid, String pwd, String timestamp) {
        // 加密后的字符串
        String encryptPwd = null;
        try {
            System.out.println(userid.toUpperCase());
            String passwordStr = userid.toUpperCase() + "00000000" + pwd + timestamp;
            // 对密码进行加密
            encryptPwd = getMD5Str(passwordStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回加密字符串
        return encryptPwd;
    }

    /**
     * @param str 需要加密的字符串
     * @return 返回加密后的字符串
     * @description MD5加密方法
     */
    private static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        // 加密前的准备
        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            //初始化加密类失败，返回null。
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            //初始化加密类失败，返回null。
            return null;
        }

        byte[] byteArray = messageDigest.digest();

        // 加密后的字符串
        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }

        return md5StrBuff.toString();
    }


    /**
     * @param obj        请求对象
     * @param httpUrl    请求地址
     * @param methodName 方法名
     * @return 请求网关返回的值
     * @throws Exception
     * @description 短连接发送(不进行URLENCODE)
     */
    private String executeNotKeepAliveNotUrlEncodePost(Message message, String httpUrl, String methodName) throws Exception {
        String result = String.valueOf(ERROR_310099);
        HttpClient httpclient = null;
        try {
            //XML请求报文
            StringBuffer requestSb = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            //发送增加标签
            if ("single_send".equals(methodName) || "batch_send".equals(methodName) || "multi_send".equals(methodName)) {
                requestSb.append("<mtreq>");
            } else if ("get_mo".equals(methodName)) {
                //上行
                requestSb.append("<moreq>");
            } else if ("get_rpt".equals(methodName)) {
                //状态报告
                requestSb.append("<rptreq>");
            } else if ("get_balance".equals(methodName)) {
                //余额
                requestSb.append("<feereq>");
            }

            //根据是否有值，生成XML报文
            // 用户账号
            if (message.getUserid() != null && !"".equals(message.getUserid())) {
                requestSb.append("<userid>").append(message.getUserid()).append("</userid>");
            }
            // 用户密码
            if (message.getPwd() != null && !"".equals(message.getPwd())) {
                requestSb.append("<pwd>").append(message.getPwd()).append("</pwd>");
            }
            //短信接收的手机号
            if (message.getMobile() != null && !"".equals(message.getMobile())) {
                requestSb.append("<mobile>").append(message.getMobile()).append("</mobile>");
            }
            //短信内容
            if (message.getContent() != null && !"".equals(message.getContent())) {
                requestSb.append("<content>").append(message.getContent()).append("</content>");
            }
            //时间戳
            if (message.getTimestamp() != null && !"".equals(message.getTimestamp())) {
                requestSb.append("<timestamp>").append(message.getTimestamp()).append("</timestamp>");
            }
            // 扩展号
            if (message.getExno() != null && !"".equals(message.getExno())) {
                requestSb.append("<exno>").append(message.getExno()).append("</exno>");
            }
            //用户自定义流水编号
            if (message.getCustid() != null && !"".equals(message.getCustid())) {
                requestSb.append("<custid>").append(message.getCustid()).append("</custid>");
            }
            //自定义扩展数据
            if (message.getExdata() != null && !"".equals(message.getExdata())) {
                requestSb.append("<exdata>").append(message.getExdata()).append("</exdata>");
            }
            //获取上行或者状态报告的最大条数
            if (message.getRetsize() != null) {
                requestSb.append("<retsize>").append(message.getRetsize()).append("</retsize>");
            }

            //业务类型
            if (message.getSvrtype() != null && !"".equals(message.getSvrtype())) {
                requestSb.append("<svrtype>").append(message.getSvrtype()).append("</svrtype>");
            }
            //apikey
            if (message.getApikey() != null && !"".equals(message.getApikey())) {
                requestSb.append("<apikey>").append(message.getApikey()).append("</apikey>");
            }

            //个性化群发包体报文的生成
            if (message.getMultimt() != null && message.getMultimt().size() > 0) {
                requestSb.append("<multimt>");
                MultiMt multiMt = null;
                for (int i = 0; i < message.getMultimt().size(); i++) {
                    multiMt = message.getMultimt().get(i);
                    requestSb.append("<mt>");
                    // 短信接收的手机号
                    if (multiMt.getMobile() != null && !"".equals(multiMt.getMobile())) {
                        requestSb.append("<mobile>").append(multiMt.getMobile()).append("</mobile>");
                    }
                    // 短信内容
                    if (multiMt.getContent() != null && !"".equals(multiMt.getContent())) {
                        requestSb.append("<content>").append(multiMt.getContent()).append("</content>");
                    }
                    // 扩展号
                    if (multiMt.getExno() != null && !"".equals(multiMt.getExno())) {
                        requestSb.append("<exno>").append(multiMt.getExno()).append("</exno>");
                    }
                    // 用户自定义流水编号
                    if (multiMt.getCustid() != null && !"".equals(multiMt.getCustid())) {
                        requestSb.append("<custid>").append(multiMt.getCustid()).append("</custid>");
                    }
                    // 自定义扩展数据
                    if (multiMt.getExdata() != null && !"".equals(multiMt.getExdata())) {
                        requestSb.append("<exdata>").append(multiMt.getExdata()).append("</exdata>");
                    }
                    //业务类型
                    if (multiMt.getSvrtype() != null && !"".equals(multiMt.getSvrtype())) {
                        requestSb.append("<svrtype>").append(multiMt.getSvrtype()).append("</svrtype>");
                    }
                    requestSb.append("</mt>");
                }
                requestSb.append("</multimt>");
            }
            //发送增加标签
            if ("single_send".equals(methodName) || "batch_send".equals(methodName) || "multi_send".equals(methodName)) {
                requestSb.append("</mtreq>");
            } else if ("get_mo".equals(methodName)) {
                //上行
                requestSb.append("</moreq>");
            } else if ("get_rpt".equals(methodName)) {
                //状态报告
                requestSb.append("</rptreq>");
            } else if ("get_balance".equals(methodName)) {
                //余额
                requestSb.append("</feereq>");
            }

            String entityValue = requestSb.toString();
            // 定义请求头
            HttpPost httppost = new HttpPost(httpUrl);
            httppost.setHeader("Content-Type", "text/xml");


            StringEntity stringEntity = new StringEntity(entityValue, HTTP.UTF_8);

            // 设置参数的编码UTF-8
            httppost.setEntity(stringEntity);

            // 创建连接
            httpclient = new DefaultHttpClient();

            // 设置请求超时时间 设置为5秒
            httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, HTTP_REQUEST_TIMEOUT);
            // 设置响应超时时间 设置为60秒
            httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, HTTP_RESPONSE_TIMEOUT);

            HttpEntity entity = null;
            HttpResponse httpResponse = null;

            try {
                // 向网关请求
                httpResponse = httpclient.execute(httppost);
                // 若状态码为200，则代表请求成功
                if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == 200) {
                    //获取响应的实体
                    entity = httpResponse.getEntity();
                    //响应的内容不为空，并且响应的内容长度大于0,则获取响应的内容
                    if (entity != null && entity.getContentLength() > 0) {
                        try {
                            //请求成功，能获取到响应内容
                            result = EntityUtils.toString(entity);
                        } catch (Exception e) {
                            e.printStackTrace();
                            //获取内容失败，返回空字符串
                            result = "";
                        }
                    } else {
                        //请求成功，但是获取不到响应内容
                        result = "";
                    }
                } else {
                    // 设置错误码
                    result = String.valueOf(ERROR_310099);
                    System.out.println("请求失败：" + httpResponse.getStatusLine().toString());
                }

            } catch (Exception e) {
                result = String.valueOf(ERROR_310099);
                e.printStackTrace();
            }
        } catch (Exception e) {
            result = String.valueOf(ERROR_310099);
            e.printStackTrace();
        } finally {
            // 关闭连接
            if (httpclient != null) {
                try {
                    httpclient.getConnectionManager().shutdown();
                } catch (Exception e2) {
                    // 关闭连接失败
                    e2.printStackTrace();
                }

            }
        }
        return result;

    }

    /**
     * 查询剩余金额或条数
     *
     * @param userId    用户账号
     * @param password  用户密码
     * @param timestamp 时间戳  密码加密时，才需要传值
     * @return 0或者正数:成功 负数:返回错误代码
     * @description
     */
    public Remains getRemains(String userId, String password, String timestamp) {
        // 定义返回值，默认查询余额失败
        Remains remains = new Remains();
        try {
            Message message = new Message();

            //时间戳不为空，则设置时间戳
            if (timestamp != null && !"".equals(timestamp.trim())) {
                message.setTimestamp(timestamp);
            }

            // 将 userid转成大写,以防大小写不一致
            userId = userId.toUpperCase();
            //设置账号
            message.setUserid(userId);

            // 设置密码
            message.setPwd(password);

            // 网关返回值信息
            String Message = null;
            StringBuffer messageBuffer = new StringBuffer("");

            int resultInt = getMoRptFeeByNotKeepAlive("get_balance", message, messageBuffer);

            // returnInt为0,代表提交成功;returnInt不为0，代表提交失败
            if (resultInt == 0) {
                // 提交成功
                Message = messageBuffer.toString();
                // 解析json
                if (Message != null && !"".equals(Message.trim())) {
                    //解析XML
                    Document doc = DocumentHelper.parseText(Message);
                    Element mtrsp = doc.getRootElement();
                    // 获取是否成功标识
                    int result = Integer.parseInt(mtrsp.element("result").getText());
                    //设置查询余额状态为成功
                    remains.setResult(result);
                    // result为0，则查询余额成功
                    if (result == 0) {
                        //获取计费类型
                        int chargetype = Integer.parseInt(mtrsp.element("chargetype").getText());
                        //设置计费类型
                        remains.setChargetype(chargetype);

                        //剩余条数
                        int balance = Integer.parseInt(mtrsp.element("balance").getText());
                        //设置剩余条数
                        remains.setBalance(balance);

                        //剩余金额
                        String money = mtrsp.element("money").getText();
                        //设置剩余金额
                        remains.setMoney(money);
                    }
                }

            } else {
                // 提交失败，返回错误码
                return new Remains(resultInt);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // 出现异常，查询余额失败
            remains.setResult(ERROR_310099);
        }
        return remains;
    }

}
