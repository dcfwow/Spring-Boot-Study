package com.zys.day4.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "other")
@PropertySource("classpath:other.properties")//指明配置文件地址
@Getter
@Setter
public class OtherProperties {
    private String title;
    private String blog;
}
