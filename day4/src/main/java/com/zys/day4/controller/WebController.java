package com.zys.day4.controller;

import com.zys.day4.model.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WebController {

    @RequestMapping(name = "/getUser",method = RequestMethod.POST)
    public User getUser(){
        User user = new User();
        user.setName("小明");
        user.setAge(18);
        user.setPass("123456");
        return user;
    }

    @RequestMapping("/getUsers")
    public List<User> getUsers(){
        List<User> users = new ArrayList<User>();
        User user1=new User();
        user1.setName("neo");
        user1.setAge(30);
        user1.setPass("neo123");
        users.add(user1);
        User user2=new User();
        user2.setName("小明");
        user2.setAge(12);
        user2.setPass("123456");
        users.add(user2);
        return users;
    }

    @RequestMapping(value = "get/{name}",method = RequestMethod.GET)
    public String getName(@PathVariable String name){
        return name;
    }

    /**
     * @Valid 代表此对象使用了参数校验。
     * 参数校验的结果储存在BindingResult中，可以根据属性判断是否校验通过，如不通过，可以将错误信息打印出来
     * @param user
     * @param result
     */
    @RequestMapping("/saveUser")
    public void saveUser(@Valid User user, BindingResult result){
        System.out.println("user:"+user.toString());
        if (result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error:list) {
                System.out.println(error.getCode()+"-"+error.getDefaultMessage());
            }
        }
    }


}
