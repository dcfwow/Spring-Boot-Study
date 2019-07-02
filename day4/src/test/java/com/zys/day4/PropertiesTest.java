package com.zys.day4;

import com.zys.day4.properties.NeoProperties;
import com.zys.day4.properties.OtherProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {
    @Value("${neo.title}")//读取application.properties或application.yml的neo.title属性值，并赋值给title
    private String title;
    @Resource
    private NeoProperties properties;
    @Resource
    private OtherProperties otherProperties;

    @Test
    public void testSingle(){
        Assert.assertEquals(title,"dcfwow");//判断属性值是否和目标值一致
    }

    @Test
    public void testMore(){
        System.out.println("Title:"+properties.getTitle()+"\n"+"Description:"+properties.getDescription());
    }

    @Test
    public void testOther(){
        System.out.println("Title:"+otherProperties.getTitle()+"\n"+"Blog:"+otherProperties.getBlog());
    }

}
