package com.JiCode.ProductDev;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("com.JiCode.ProductDev.adaptor.output.dataaccess.mappers")
@ComponentScan(basePackages = {"com.JiCode.ProductDev.adaptor.output.dataaccess.mappers"})
@ComponentScan("com.JiCode.ProductDev.domain")
@EntityScan({"com.JiCode.ProductDev.service"})
public class ProductDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductDevApplication.class, args);
	}

}
