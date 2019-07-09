package com.zys.day16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 添加 SpringBootServletInitializer 是为了支持将项目打包成独立的 war 在 Tomcat 中运行
 */
@SpringBootApplication
public class Day16Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Day16Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Day16Application.class, args);
    }
}
