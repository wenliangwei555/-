package com.msghub.msghub.config.wechatconfig;


import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;


/**
 * GetToken发送请求，将得到的access_token和expires_in放进实体类
 * * @Author 温莨
 *
 * @Date 2020/1/31 16:02
 * @Version 1.0
 */
@Component
public class GetToken {
    /**
     * @param appid
     * @param appSecrect
     * @return
     */
    public AccessToken getToken(String appid, String appSecrect) {
        AccessToken token = null;
        String url = WeixinUrl.ACCESS_TOKEN + "?grant_type=client_credential&appid=" + appid + "&secret=" + appSecrect;
        String result = CommonUtil.httpsRequest(url, "GET", null);
        JSONObject jsonObject = JSONObject.fromObject(result);
        System.out.println(jsonObject);
        if (jsonObject != null) {
            try {
                token = new AccessToken();
                token.setAccesstoken(jsonObject.getString("access_token"));
                token.setExpiresin(jsonObject.getLong("expires_in"));
            } catch (Exception e) {
                token = null;
                e.printStackTrace();
                System.out.println("系统出错");
            }
        } else {
            token = null;
            // 获取token失败
            System.err.println("获取token失败 jsonObject为空，获取token失败");
        }
        return token;
    }

}
