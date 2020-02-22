package com.msghub.msghub.config.wechatconfig;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 *  @Author 温莨
 *
 * @Date 2020/1/31 16:02
 * @Version 1.0
 */
public class CommonUtil {

    /**
     * access_token的失效时间
     */
    private static long expiresTime;
    /**
     * //缓存的access_token
     */
    private static String accessToken;

    /**
     * @param url
     * @param get
     * @param o
     * @return
     */
    public static String httpsRequest(String url, String get, Object o) {
        String message = "";
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            // 必须是get方式请求
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            // 连接超时30秒
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
            // 读取超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            message = new String(jsonBytes, "UTF-8");
            is.close();
        } catch (Exception e) {
            System.out.println("获取access_token发生异常--------------" + e.toString());
        }
        return message;
    }

}
