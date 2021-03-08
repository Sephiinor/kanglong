package com.sephinor.kanglong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = "com.sephinor.kangkong.service.api")
public class KanglongWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanglongWebApplication.class, args);
	}

}
