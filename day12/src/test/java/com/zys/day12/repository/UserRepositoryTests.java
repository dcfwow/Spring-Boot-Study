package com.zys.day12.repository;

import com.zys.day12.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JdbcTemplate primaryJdbcTemplate;
    @Autowired
    private JdbcTemplate secondaryJdbcTemplate;

    @Test
    public void testSave() {
        User user =new User("wow","123456",19);
        userRepository.saveUser(user,primaryJdbcTemplate);
        userRepository.saveUser(user,secondaryJdbcTemplate);
    }

    @Test
    public void testUpdate() {
        User primaryUser =new User("wower","123456",18);
        primaryUser.setId(2L);
        userRepository.update(primaryUser,primaryJdbcTemplate);
        User secondaryUser =new User("wower","123456",18);
        secondaryUser.setId(1L);
        userRepository.update(primaryUser,secondaryJdbcTemplate);
    }

    @Test
    public void testDelete() {
        userRepository.delete(1L,primaryJdbcTemplate);
        userRepository.delete(1l,secondaryJdbcTemplate);
    }

    @Test
    public void testQueryOne()  {
        User primaryUser=userRepository.findById(2L,primaryJdbcTemplate);
        User secondaryUser=userRepository.findById(1l,secondaryJdbcTemplate);
        System.out.println("user == "+primaryUser.toString());
        System.out.println("user == "+secondaryUser.toString());
    }

    @Test
    public void testQueryAll()  {
        List<User> primaryUsers=userRepository.findAll(primaryJdbcTemplate);
        for (User user:primaryUsers){
            System.out.println("user == "+user.toString());
        }
        List<User> secondaryUsers=userRepository.findAll(secondaryJdbcTemplate);
        for (User user:secondaryUsers){
            System.out.println("user == "+user.toString());
        }
    }

}
