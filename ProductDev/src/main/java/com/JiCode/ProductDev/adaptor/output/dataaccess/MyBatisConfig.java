package com.JiCode.ProductDev.adaptor.output.dataaccess;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "com.JiCode.ProductDev.adaptor.output.dataaccess.mappers")
public class MyBatisConfig {

    @Autowired
    private DataSource dataSource; // 这里会自动注入 MSDataSourceConf 中定义的数据源

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        System.out.println("\033[31m" + "这是红色的文字" + "\033[0m");
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource); // 设置数据源
        factoryBean.setTypeAliasesPackage("com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels");
        System.out.println("\033[31m" + "这是红色的文字" + "\033[0m");
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:com/JiCode/ProductDev/**/*.xml"));
        return factoryBean.getObject();
    }
}