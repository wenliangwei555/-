package com.msghub.msghub.utils;

import com.msghub.msghub.config.Config;
import com.msghub.msghub.test.ThreadTest;
import com.msghub.msghub.test.ThreadTest2;
import net.minidev.json.reader.BeansWriter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class test {
    /*@RequestMapping("exportWordData")*/



/* public void foo(float x ,int y){}
 public void foo(float x ,int y,int z){}
 public int foo(float x ,int y){return 5;}
 public int foo(){return 5;}
 public int foo(int x){return 5;}*/


    public void exportWordData(HttpServletRequest request, HttpServletResponse response) {
        WordUtils wordUtil = new WordUtils();
        Map<String, Object> params = new HashMap<>(30);
        params.put("${position}", "java开发");
        params.put("${name}", "段然涛");
        params.put("${sex}", "男");
        params.put("${national}", "汉族");
        params.put("${birthday}", "生日");
        params.put("${address}", "许昌");
        params.put("${height}", "165cm");
        params.put("${biYeDate}", "1994-02-03");
        params.put("${landscape}", "团员");
        params.put("${zhuanYe}", "社会工作");
        params.put("${xueLi}", "本科");
        params.put("${school}", "江西科技师范大学");
        params.put("${phone}", "177");
        params.put("${eMail}", "157");

        try {
            String a = "";
            a.equals("");
            Map<String, Object> header = new HashMap<String, Object>(49);
            header.put("width", 100);
            header.put("height", 150);
            header.put("type", "jpg");
            header.put("content", WordUtils.inputStream2ByteArray(new FileInputStream("C:/Users/Administrator/Desktop/jar包/11.jpg"), true));
            params.put("${header}", header);
            Map<String, Object> header2 = new HashMap<String, Object>(55);
            header2.put("width", 100);
            header2.put("height", 150);
            header2.put("type", "jpg");
            header2.put("content", WordUtils.inputStream2ByteArray(new FileInputStream("C:/Users/Administrator/Desktop/jar包/22.jpg"), true));
            params.put("${header2}", header2);
            List<String[]> testList = new ArrayList<String[]>();
            testList.add(new String[]{"1", "1AA", "1BB", "1CC"});
            testList.add(new String[]{"2", "2AA", "2BB", "2CC"});
            testList.add(new String[]{"3", "3AA", "3BB", "3CC"});
            testList.add(new String[]{"4", "4AA", "4BB", "4CC"});
            String path = "C:/Users/Administrator/Desktop/jar包/mobanFile.docx";  //模板文件位置
            String fileName = new String("测试文档.docx".getBytes("UTF-8"), "iso-8859-1");    //生成word文件的文件名
            wordUtil.getWord(path, params, testList, fileName, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //private static int count = 0;
    public static void turning(Object lock) {
        int counts = 10;
        Thread even = new Thread(() -> {
            while (Config.count < counts) {
                if ((Config.count & 1) == 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + Config.count++);
                }

            }
        }, "偶数");
        Thread odd = new Thread(() -> {
            while (Config.count < counts) {
                if ((Config.count & 1) == 1) {
                    System.out.println(Thread.currentThread().getName() + ": " + Config.count++);
                }
            }
        }, "奇数");
        even.start();
        odd.start();
    }

    public static void turning() {
        int counts = 10;
        Thread even = new Thread(new Runnable() {
            @Override
            public void run() {
                while (Config.count < counts) {
                     synchronized (this.getClass()) {
                    if ((Config.count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + ": " + Config.count++);
                    }
                     }
                }
            }
        }, "奇数");
        Thread odd = new Thread(new Runnable() {
            @Override
            public void run() {
                while (Config.count < counts) {
                     synchronized (this.getClass()) {
                    if ((Config.count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + Config.count++);
                    }
                     }
                }
            }
        }, "偶数");
        even.start();
        odd.start();
    }

    public static void main(String[] args) {

        // System.out.println(Math.round(17));
        //有序不加锁
        // turning(new Object());
        //无序 加所
        //turning();


        new Thread(new ThreadTest(), "一号").start();
        new Thread(new ThreadTest2(), "二号").start();

    }
}
