package com.msghub.msghub.utils;

import com.msghub.msghub.model.nationwide.Complete;
import net.sf.json.JSONObject;
import org.apache.poi.ss.formula.functions.T;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 温莨
 * @Date 2020/2/11 12:33
 * @Version 1.0
 */
public class MyUtil {
    /**
     * 私有构造 禁止创建 MyUtil
     */
    private MyUtil() {
        throw new AssertionError();
    }

    /**
     * 使用序列化来解决多层克隆 递归性质
     * 将对象序列化成流是的是对象是原对象的一个克隆体  原来的对象仍然在虚拟机中  来实现克隆
     *
     * @param obj 传入的目标对象
     * @param <T> 任意类型
     * @return 返回一个目标对象  必须实现序列化接口 @Serializable
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T obj) throws Exception {
        //字节数组流输出
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        //对象输出流
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        //序列化
        oos.writeObject(obj);
        //字节数组输入
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        //对象输入
        ObjectInputStream ois = new ObjectInputStream(bin);
        //反序列化 返回
        return (T) ois.readObject();
// 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没有任何意义
// 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
    }

    /**
     * java.io.StreamCorruptedException: invalid type code: AC问题解决
     *
     * @param file
     * @param p
     * @throws Exception
     */
    public static void set(File file, Object p) throws Exception {
        FileOutputStream fos = new FileOutputStream(file, true);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(p);
        oos.close();
    }

    /**
     * java.io.StreamCorruptedException: invalid type code: AC问题解决
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static List<Object> get(File file) throws Exception {
        List<Object> list = new ArrayList<Object>();
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = null;
        while (fis.available() > 0) {
            //每次都new一个新的ObjectInputStream
            ois = new ObjectInputStream(fis);
            Object p = ois.readObject();
            list.add(p);
        }
        ois.close();
        return list;
    }

    /**
     *
     * @param s
     * @param clazz
     * @return
     */
    public static Object tojson(String s, Class<?> clazz) {
        JSONObject jsonObject = JSONObject.fromObject(s);
        return JSONObject.toBean(jsonObject, clazz);
    }

    /**
     * @param url
     * @param
     * @param
     * @return
     */
    public static String httpsRequest(String url) throws Exception {
        String message = "";
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
        message = message + "}";
        return message;
    }


}

