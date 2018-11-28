package com.xkf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RabbitProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitProducerApplication.class, args);
	}
}
