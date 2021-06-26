package com.sephinor.kanglong.kanglonguser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.sephinor.kanglong.kanglonguser.mapper")
public class KanglongUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanglongUserApplication.class, args);
    }

}
