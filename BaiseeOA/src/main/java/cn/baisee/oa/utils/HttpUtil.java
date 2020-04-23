package cn.baisee.oa.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;
/**
 * http 工具类
 */
public class HttpUtil {
	private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	public static String postGeneralUrl(String generalUrl) throws Exception {
		String contentType = "application/x-www-form-urlencoded";
		URL url = new URL(generalUrl);
		// 打开和URL之间的连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		// 设置通用的请求属性
		connection.setRequestProperty("Content-Type", contentType);
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.setUseCaches(false);
		connection.setDoOutput(true);
		connection.setDoInput(true);

		// 得到请求的输出流对象
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		// out.write(params.getBytes(encoding));
		out.flush();
		out.close();

		// 建立实际的连接
		connection.connect();
		// 获取所有响应头字段
		Map<String, List<String>> headers = connection.getHeaderFields();
		// 遍历所有的响应头字段
		for (String key : headers.keySet()) {
			System.out.println(key + "--->" + headers.get(key));
		}
		// 定义 BufferedReader输入流来读取URL的响应
		BufferedReader in = null;
		in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String result = "";
		String getLine;
		while ((getLine = in.readLine()) != null) {
			result += getLine;
		}
		in.close();
		System.out.println("result:" + result);
		return result;
	}
	public static String doPostJson(String url, JSONObject param) {
	        HttpPost httpPost = null;
	        String result = null;
	        try {
	           // HttpClient client = new DefaultHttpClient(); 
	        	HttpClient client = HttpClientBuilder.create().build();//获取DefaultHttpClient请求
	            httpPost = new HttpPost(url);
	            if (param != null) {
	                StringEntity se = new StringEntity(param.toString(), "utf-8");
	                httpPost.setEntity(se); // post方法中，加入json数据
	                httpPost.setHeader("Content-Type", "application/json");
	            }
	            
	            HttpResponse response = client.execute(httpPost);
	            if (response != null) {
	                HttpEntity resEntity = response.getEntity();
	                if (resEntity != null) {
	                    result = EntityUtils.toString(resEntity, "utf-8");
	                }
	            }
	            
	        } catch (Exception ex) {
	            System.err.println("HttpUtil dopost Invoke interface failed!");
	            logger.error("HttpUtil dopost Invoke interface failed!");
	        }
	        return result;
	    }
	public static void main(String[] args) throws Exception {
		
	}
}
