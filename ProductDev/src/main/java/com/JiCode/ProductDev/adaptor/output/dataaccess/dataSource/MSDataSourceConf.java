package com.JiCode.ProductDev.adaptor.output.dataaccess.dataSource;

/**
 * 在这段代码中，dataSource 方法负责创建并返回一个 DataSource 对象。这个方法首先创建一个 MSDataSource 对象，然后配置它的主库和从库，最后返回这个配置好的 DataSource 对象。这个过程就是工厂模式的体现。
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableConfigurationProperties(MSDataSourceProperties.class) // 指定要加载的配置类
public class MSDataSourceConf {
    @Bean
    public DataSource dataSource(MSDataSourceProperties properties) {

        MSDataSource dataSource = new MSDataSource();

        // 主数据库
        // HikariDataSource 是高性能JDBC数据源，这里配置一下
        // properties.getMaster() 方法返回的是主数据库的配置信息
        dataSource.setDefaultTargetDataSource(new HikariDataSource(new HikariConfig(properties.master())));

        // 从数据库
        Map<Object, Object> slaveDataSource = new HashMap<>();

        // 从数据库 Key ，先置空
        dataSource.setSlaveKeys(new ArrayList<>());

        // 遍历配置，把所有从数据库塞进数据库源
        for (Map.Entry<String, Properties> entry : properties.slave().entrySet()) {

            if (slaveDataSource.containsKey(entry.getKey())) {
                throw new IllegalArgumentException("存在同名的从数据库定义：" + entry.getKey());
            }

            slaveDataSource.put(entry.getKey(), new HikariDataSource(new HikariConfig(entry.getValue())));

            dataSource.getSlaveKeys().add(entry.getKey());
        }

        // 设置从库
        dataSource.setTargetDataSources(slaveDataSource);

        return dataSource;
    }
}