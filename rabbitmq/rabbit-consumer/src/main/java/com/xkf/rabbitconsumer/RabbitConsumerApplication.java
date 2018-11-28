package com.xkf.rabbitconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RabbitConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(RabbitConsumerApplication.class, args);
	}
}
