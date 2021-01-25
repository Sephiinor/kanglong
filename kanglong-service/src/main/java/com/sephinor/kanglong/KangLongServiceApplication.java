package com.sephinor.kanglong;


import com.sephinor.common.entity.Brand;
import com.sephinor.kanglong.service.BrandService;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import tk.mybatis.spring.annotation.MapperScan;

/**
 *
 */
@SpringBootApplication
@MapperScan("com.sephinor.kanglong.mapper")
@EnableDiscoveryClient
public class KangLongServiceApplication {
	public static void main(String[] args) {

		SpringApplication.run(KangLongServiceApplication.class, args);


	}
}
