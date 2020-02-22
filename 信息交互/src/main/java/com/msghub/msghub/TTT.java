package com.msghub.msghub;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.msghub.msghub.model.Bill;
import com.msghub.msghub.test.ThreadTest;
import com.msghub.msghub.test.ThreadTest2;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Configuration
public class TTT extends HttpServlet {

    @Bean(name = "dataSource")
    @Qualifier(value = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "c3p0")
    public static DataSource dataSource() {
        return DataSourceBuilder.create().type(com.mchange.v2.c3p0.ComboPooledDataSource.class).build();
    }

    static Integer qq = 0;
    static List<String> list = new ArrayList<>();
    static Bill bill = new Bill();
    static Bill[] bil = new Bill[10];

    public static void main(String[] args) {
        System.out.println(  ClassLayout.parseInstance(bill).toPrintable());
        System.out.println(  ClassLayout.parseInstance(bil).toPrintable());
        /** QueryRunner qu = new QueryRunner(getDataSource());

         try {
         Long query = (Long) qu.query("select count(*) from user  ", new ScalarHandler());
         System.out.println(query);
         } catch (SQLException e) {
         e.printStackTrace();
         }*/

        ArrayList<Object> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);
        objects.forEach((Object object) -> {
            System.out.println(object);
        });




        /*-javaagent:C:\Users\温莨\Desktop\新建文件夹 (3)\idea破解\jetbrains-agent
         */

        //System.out.println( false||true);
        list.add("x");
        Collection<String> clist = Collections.unmodifiableCollection(list);
        //   clist. add("y"); // 运行时此行报错
      /*  int h;
        String key="通话";
        String key2="重地";
        HashMap hashMap = new HashMap();
       // Array hash = new Array ();
        hashMap.put("通话","1");
        hashMap.put("重地","2");
        System.out.println(hashMap.get("通话"));
        System.out.println(hashMap.get("重地"));
      //  System.out.println(hashMap.get(null));
        System.out.println((h = key.hashCode()) ^ (h >>> 16));
        System.out.println((h = key2.hashCode()) ^ (h >>> 16));*/
        //*     System.out.println((2 & 1) == 0);
        //        System.out.println((2 & 1) == 1);
        //        System.out.println((13 & 11));
        //        System.out.println((13 % 2));*/
        //        //  0
        //        //  1  10  11 100 101  110  111  1000  1001 1010
        //        //  1011  1100  1101  1110  1111  10000   10001   10010  10011  10100
        //        //  10101  10110  10111  11000  11001  11010  11011  11100  11101  11110
        //
        //        //  11110  + 11110=   111100



    }


}
