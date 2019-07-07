package com.zys.day132;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zys.day132.mapper")
public class Day132Application {

    public static void main(String[] args) {
        SpringApplication.run(Day132Application.class, args);
    }

}
