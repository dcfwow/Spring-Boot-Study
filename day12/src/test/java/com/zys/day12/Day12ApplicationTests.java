package com.zys.day12;

import com.zys.day12.model.User;
import com.zys.day12.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Day12ApplicationTests {
    /*@Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {
        User user = new User("dcfwow","123456",23);
        userRepository.saveUser(user);
    }

    @Test
    public void testUpdate(){
        User user = new User("wowlg17","123456",18);
        user.setId(1l);
        userRepository.update(user);
    }

    @Test
    public void testDelete(){
        userRepository.delete(1l);
    }

    @Test
    public void testQueryOne(){
        User user = userRepository.findById(1l);
        System.out.println("user:"+user.toString());
    }

    @Test
    public void testQueryAll(){
        List<User> users = userRepository.findAll();
        for (User user:users) {
            System.out.println("user:"+user.toString());
        }
    }*/
}
