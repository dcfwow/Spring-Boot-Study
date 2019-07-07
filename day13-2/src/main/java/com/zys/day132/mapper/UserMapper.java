package com.zys.day132.mapper;

import com.zys.day132.enums.UserSexEnum;
import com.zys.day132.model.User;
import com.zys.day132.param.UserParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

//单数据源配置Mapper
public interface UserMapper {
   /* @Delete("DELETE FROM users WHERE id = #{id}")
    void delete(long id);

    //使用@Param为数据库字段取别名
    @Select("SELECT * FROM users WHERE user_sex = #{user_sex}")
    List<User> getListByUserSex(@Param("user_sex") String userSex);

    //使用Map传递多个参数
    @Select("SELECT * FROM users WHERE username = #{username} AND user_sex = #{user_sex}")
    List<User> getListByNameAndSex(Map<String,Object> map);

    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(User user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(User user);

    //使用@Results修饰返回的结果集，使得数据库字段和类属性一致，若一致，则不需要@Results
    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex",column = "user_sex",javaType = UserSexEnum.class),
            @Result(property = "nickName",column = "nick_name")
    })
    List<User> getAll();

    *//**
     * MyBatis中的#和$
     * #会对SQL进行预处理
     * $有SQL注入的可能性
     *//*

    //使用<script>标签
    @Update("<script>" +
            "update users" +
            "<set>" +
            "<if test=\"userName != null\">userName=#{userName},</if>" +
            "<if test=\"nickName != null\">nick_name=#{nickName},</if>" +
            "</set>" +
            "where id=#{id}" +
            "</script>")
    void updateUser(User user);

    *//**
     * 使用已经定于好的类和方法来执行sql，更加优雅的构建动态sql
     * type:动态生成sql的类
     * method:具体的方法名
     *//*
    @SelectProvider(type = UserSql.class,method = "getList")
    List<User> getList(UserParam userParam);

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name")
    })
    User getOne(Long id);

    @SelectProvider(type = UserSql.class, method = "getCount")
    int getCount(UserParam userParam);*/
}
