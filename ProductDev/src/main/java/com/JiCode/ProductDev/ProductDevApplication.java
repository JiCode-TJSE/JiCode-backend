package com.JiCode.ProductDev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ProductDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductDevApplication.class, args);
	}

}
