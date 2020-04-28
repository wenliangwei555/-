package com.msghub.msghub.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.msghub.msghub.TTT;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.activation.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

/*@ConfigurationProperties(prefix = "c3p0")*/
//@Component
public class DataSourceConfiguration {


    public static void main(String[] args) {
       // System.out.println(TTT.dataSource());
        QueryRunner qu = new QueryRunner(TTT.dataSource());
        System.out.println(qu);
        try {
            Long query = qu.query("select count(*) from user where id=?", new ScalarHandler<Long>(), "11");
            System.out.println(query+"query");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private String driver;
    private String url;
    private String username;
    private String password;

    @Bean
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setAutoCommitOnClose(false);
        dataSource.setInitialPoolSize(10);
        dataSource.setMinPoolSize(10);
        dataSource.setMaxPoolSize(100);
        System.out.print("=========================================" + dataSource + "===========================================");
        return dataSource;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
