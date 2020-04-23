package cn.baisee.oa.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


import java.util.regex.Pattern;

public class CommonUtil {
    private static Logger logger = Logger.getLogger(CommonUtil.class);
    public static String appId = "wxfb4cbd4f424700be";
    public static String secret = "f88750b3d56df1ea801b9726f06d228d";
    public static JSONObject getUrlInfo(String requestURL,String method,String json) throws Exception {
        URL get_url = new URL(requestURL);
        // 将url 以 open方法返回的urlConnection 连接强转为HttpURLConnection连接
        // (标识一个url所引用的远程对象连接)
        // 此时cnnection只是为一个连接对象,待连接中
        HttpURLConnection httpURLConnection = (HttpURLConnection) get_url
                .openConnection();
        // 设置请求方式为post
        httpURLConnection.setRequestMethod(method);
        // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
        httpURLConnection.setDoOutput(true);
        // 设置连接输入流为true
        httpURLConnection.setDoInput(true);
        // post请求缓存设为false
        httpURLConnection.setUseCaches(false);
        // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
        // application/x-javascript text/xml->xml数据
        // application/x-javascript->json对象
        // application/x-www-form-urlencoded->表单数据
        // ;charset=utf-8 必须要，不然妙兜那边会出现乱码
        httpURLConnection.setRequestProperty("Content-type",
                "application/json;charset=utf-8");
        // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
        httpURLConnection.connect();

        // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
        OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
        if(method.equals("POST")){
            out.append(json);
        }
        out.flush();
        out.close();

        // 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpURLConnection.getInputStream(), "UTF-8"));
        // 读取数据操作
        String str = null;
        StringBuffer buffer = new StringBuffer();
        while((str = reader.readLine())!= null){
            buffer.append(str);
        }
        //转换成json
        JSONObject jsonObj = JSONObject.parseObject(buffer.toString());
        reader.close();
        return jsonObj;

    }


    public static String getAccessToken() throws Exception{
        Jedis jedis = new Jedis("localhost");
        String access_token = null;
        Boolean conn_flag = true;
        try{
            logger.info("jedis "+jedis.ping());
            access_token = jedis.get("access_token_fxpt");
            if (access_token !=null && access_token.length() > 0 ){
                return access_token;
            }
        }catch(Exception e){
            logger.info("redis连接失败");
            conn_flag = false;
        }
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appId + "&secret=" + secret;
        JSONObject access_token_json = getUrlInfo(url,"GET",null);
        access_token = access_token_json.getString("access_token");
        if (conn_flag == true){
            jedis.set("access_token_fxpt", access_token);
            jedis.expire("access_token_fxpt", 7000);
        }
        return access_token;
    }




    public static String getFreight() throws Exception{
        Jedis jedis = new Jedis("localhost");
        String freight = null;
        try{
            logger.info("jedis "+jedis.ping());
            freight = jedis.get("freight");
            if (freight !=null && freight.length() > 0 ){
                return freight;
            }
        }catch(Exception e){
            logger.info("redis连接失败");
        }
        return null;
    }




    public static String getOpenId(String code) throws Exception{
        logger.debug("code "+code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ appId +"&secret="+ secret +"&code="+ code +"&grant_type=authorization_code";
        JSONObject openid_json = getUrlInfo(url,"GET",null);
        return openid_json.getString("openid");
    }

    public static JSONObject getUserInfo(String code) throws Exception{
        String access_token = getAccessToken();
        logger.debug("access_token "+access_token);
        String openid = getOpenId(code);
        logger.debug("openid "+openid);
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
        return getUrlInfo(url,"GET",null);
    }

    public static JSONObject getUserInfoByOpenid(String openid) throws Exception{
        String access_token = getAccessToken();
        logger.debug("access_token "+access_token);
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
        return getUrlInfo(url,"GET",null);
    }

    public static JSONObject createQRcode(String args,String qrtype) throws Exception{
        String access_token = getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+access_token;
        String jsonStr = null;
        TreeMap<String,String> tm = new TreeMap<String,String>();
        Map<String,Object> m = new HashMap<String, Object>();
        if (qrtype.equals("limit")){
            logger.debug("-----"+args);
            jsonStr = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\":{\"scene\": {\"scene_str\":\""+ args + "\"}}}";
            //JSONObject jsonObject = getUrlInfo(url,"POST",jsonStr);
            logger.debug("jsonStr "+jsonStr);
        }else{
        }

        return getUrlInfo(url,"POST",jsonStr);

    }


    public static Boolean downloadFile(String urlString, String filePath){
        // 构造URL
        URL url;
        try {
            url = new URL(urlString);
            // 打开连接
            URLConnection con;
            try {
                con = url.openConnection();
                // 输入流
                InputStream is = con.getInputStream();
                // 1K的数据缓冲
                byte[] bs = new byte[1024];
                // 读取到的数据长度
                int len;
                // 输出的文件流
                OutputStream os = new FileOutputStream(filePath);
                // 开始读取
                while ((len = is.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
                // 完毕，关闭所有链接
                os.close();
                is.close();
                return true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

    @Test
    public void shsh(){
        System.out.println("df "+CommonUtil.class);
        logger.info("sle");
    }

    @Test
    public static Boolean checkNum(String args){
        String pattern = "\\d+";
        boolean isMatch = Pattern.matches(pattern, args);
        if(isMatch == true){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
        return isMatch;
    }

    public static String PostSendMsg(JSONObject json, String url) {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Basic YWRtaW46");
        String result = "";
        try {
            StringEntity s = new StringEntity(json.toString(), "utf-8");
            s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.setEntity(s);
            // 发送请求
            HttpResponse httpResponse = HttpClients.createDefault().execute(post);
            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line + "\n");
            inStream.close();

            result = strber.toString();
            System.out.println(result);

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                System.out.println("请求服务器成功，做相应处理");
            } else {
                System.out.println("请求服务端失败");
            }
        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void returnMsg(HttpServletResponse response, String message) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(message);
    }
    /**
     * 获取当前网络ip
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                    logger.info("本机ip地址为："+ipAddress);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
                logger.info("请求ip地址为："+ipAddress);
            }
        }
        return ipAddress;
    }
}

