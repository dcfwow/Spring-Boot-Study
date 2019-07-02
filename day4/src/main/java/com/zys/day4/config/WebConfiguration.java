package com.zys.day4.config;

import com.zys.day4.filter.MyFilter;
import com.zys.day4.filter.MyFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Bean
    public FilterRegistrationBean testFilterRegistration(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");//监控所有的URL
        registration.setName("MyFilter");
        registration.setOrder(6);//设定过滤器执行顺序的权重值，值越小优先级越高。
        return registration;
    }

    @Bean
    public FilterRegistrationBean test2FilterRegistration(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter2());
        registration.addUrlPatterns("/*");//监控所有的URL
        registration.setName("MyFilter2");
        registration.setOrder(1);//设定过滤器执行顺序的权重值，值越小优先级越高。
        return registration;
    }
}
