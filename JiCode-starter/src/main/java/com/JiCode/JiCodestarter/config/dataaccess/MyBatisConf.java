package com.JiCode.JiCodestarter.config.dataaccess;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@AutoConfiguration
@EnableConfigurationProperties(MyBatisProperties.class) // 指定要加载的配置类
@MapperScan(basePackages = "${app.mybatis.basePackage}")
public class MyBatisConf {

    @Autowired
    private DataSource dataSource; // 这里会自动注入 MSDataSourceConf 中定义的数据源

    // 自动注入配置
    @Autowired
    private MyBatisProperties myBatisConfigProperties;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage(myBatisConfigProperties.getTypeAliasesPackage());
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(myBatisConfigProperties.getMapperLocations()));
        return factoryBean.getObject();
    }
}