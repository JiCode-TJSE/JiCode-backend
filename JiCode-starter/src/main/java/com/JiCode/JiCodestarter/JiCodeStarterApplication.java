package com.JiCode.JiCodestarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class JiCodeStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(JiCodeStarterApplication.class, args);
	}

}
