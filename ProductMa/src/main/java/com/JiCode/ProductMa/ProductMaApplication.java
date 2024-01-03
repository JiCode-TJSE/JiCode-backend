package com.JiCode.ProductMa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@MapperScan("com.JiCode.ProductMa.adaptor.output.dataaccess.mappers")
public class ProductMaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMaApplication.class, args);
	}

}
