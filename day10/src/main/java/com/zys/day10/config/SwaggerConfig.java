package com.zys.day10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//启动时加载此类
@Configuration
//此项目启用Swagger API文档
@EnableSwagger2
public class SwaggerConfig {

    //在启动时初始化，返回实例Docket(Swagger API摘要)
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //指定需要扫描包的路径，只有此路径下的Controller类才能自动生成Swagger API文档
                .apis(RequestHandlerSelectors.basePackage("com.zys.day10"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //标题
                .title("客户管理")
                //描述
                .description("客户管理中心 API 1.0 操作文档")
                //服务条款网址
                .termsOfServiceUrl("https://github.com/dcfwow/spring-boot-study")
                //版本
                .version("1.0")
                //联系方式
                .contact(new Contact("dcfwow","https://github.com/dcfwow/spring-boot-study","1559300216@qq.com"))
                .build();
    }
}
