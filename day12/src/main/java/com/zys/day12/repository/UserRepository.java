package com.zys.day12.repository;

import com.zys.day12.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface UserRepository {
    int saveUser(User user, JdbcTemplate jdbcTemplate);
    int delete(long id,JdbcTemplate jdbcTemplate);
    int update(User user,JdbcTemplate jdbcTemplate);
    List<User> findAll(JdbcTemplate jdbcTemplate);
    User findById(long id,JdbcTemplate jdbcTemplate);
}
