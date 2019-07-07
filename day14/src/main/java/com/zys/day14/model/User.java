package com.zys.day14.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity //标记一个数据库表对应的实体类，必须
@Data
public class User {
    @Id //定义映射到数据库主键的属性，必须，唯一
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true) //描述了对数据库字段的详细定义
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = true, unique = true)
    private String nickName;
    @Column(nullable = false)
    private String regTime;

    public User() {
    }

    public User(String userName, String passWord, String email, String nickName, String regTime) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.nickName = nickName;
        this.regTime = regTime;
    }
}
