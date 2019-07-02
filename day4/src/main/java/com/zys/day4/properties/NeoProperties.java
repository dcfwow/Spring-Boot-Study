package com.zys.day4.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component//定义实例
@ConfigurationProperties(prefix = "neo")//表示以neo开头的属性会自动赋值到对象的属性中
@Getter
@Setter
public class NeoProperties {
    private String title;
    private String description;
}
