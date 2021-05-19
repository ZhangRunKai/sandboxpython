package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mapper")
public class SandboxpythonApplication {


	public static void main(String[] args) {
		SpringApplication.run(SandboxpythonApplication.class, args);
    }

}
