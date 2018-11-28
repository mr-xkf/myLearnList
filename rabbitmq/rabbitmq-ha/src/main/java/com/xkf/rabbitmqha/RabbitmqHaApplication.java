package com.xkf.rabbitmqha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.xkf.rabbitmqha.ha.dao.mapper")
@EnableScheduling
public class RabbitmqHaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqHaApplication.class, args);
	}
}