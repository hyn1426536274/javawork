package com.example.javawork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.javawork.mapper")
public class JavaworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaworkApplication.class, args);
	}

}
