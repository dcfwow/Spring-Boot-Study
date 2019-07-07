package com.zys.day12.repository.impl;

import com.zys.day12.model.User;
import com.zys.day12.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 如果参数中有一个jdbcTemplate，则使用该参数，若为空，则使用默认的jdbcTemplate
 */
@Repository//标注为数据访问组件
public class UserRepositoryImpl implements UserRepository {
    @Autowired//注入JdbcTemplate(Spring提供操作JDBC的工具类)
    private JdbcTemplate primaryJdbcTemplate;

    @Override
    public int saveUser(User user,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate == null){
            jdbcTemplate = primaryJdbcTemplate;
        }
        return jdbcTemplate.update("INSERT into users(name,password,age) values(?,?,?)",
                user.getName(),user.getPassword(),user.getAge());
    }

    @Override
    public int delete(long id,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate == null){
            jdbcTemplate = primaryJdbcTemplate;
        }
        return jdbcTemplate.update("delete from users where id = ?",id);
    }

    @Override
    public int update(User user,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate == null){
            jdbcTemplate = primaryJdbcTemplate;
        }
        return jdbcTemplate.update("update users set name = ? , password = ? , age = ? where id = ?",
                user.getName(),user.getPassword(),user.getAge(),user.getId());
    }

    //使用一个内部类封装返回的结果集
    @Override
    public List<User> findAll(JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate == null){
            jdbcTemplate = primaryJdbcTemplate;
        }
        return jdbcTemplate.query("select * from users", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setAge(resultSet.getInt("age"));
                return user;
            }
        });
    }

    //使用new BeanPropertyRowMapper<User>(User.class)对返回的数据进行封装
    @Override
    public User findById(long id,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate == null){
            jdbcTemplate = primaryJdbcTemplate;
        }
        return jdbcTemplate.queryForObject("select * from users where id = ?",new Object[]{id},new BeanPropertyRowMapper<User>(User.class));
    }
}
