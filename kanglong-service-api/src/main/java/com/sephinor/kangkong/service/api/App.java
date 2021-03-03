package com.sephinor.kangkong.service.api;


import com.sephinor.common.entity.Brand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
public class App {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(App.class,args) ;
        BrandService brandService = ac.getBean(BrandService.class) ;
        Brand b = brandService.findById(1115L);
        System.out.println(b.getName());
    }
}
