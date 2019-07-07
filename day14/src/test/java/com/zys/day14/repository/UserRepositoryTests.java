package com.zys.day14.repository;

import com.zys.day14.model.User;
import com.zys.day14.repository.test1.UserTest1Repository;
import com.zys.day14.repository.test2.UserTest2Repository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {
    //单数据源测试
    @Resource
    private UserRepository userRepository;

    @Test
    public void test(){
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("aa","aa@126.com","aa","aa123456",formattedDate));
        userRepository.save(new User("bb","bb@126.com","bb","bb123456",formattedDate));
        userRepository.save(new User("cc","cc@126.com","cc","cc123456",formattedDate));

        Assert.assertEquals(3,userRepository.findAll().size());
        Assert.assertEquals("bb123456",userRepository.findByUserNameOrEmail("bb","bb@126.com").getNickName());
        userRepository.delete(userRepository.findByUserName("aa"));
    }

    /**
     * Sort，控制分页数据的排序，可以选择升序和降序。
     * PageRequest，控制分页的辅助类，可以设置页码、每页的数据条数、排序等。
     */
    @Test
    public void testPageQuery()  {
        int page=1,size=2;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        userRepository.findALL(pageable);
        userRepository.findByNickName("aa123456", pageable);
    }

    //多数据源测试
/*    @Resource
    private UserTest1Repository userTest1Repository;
    @Resource
    private UserTest2Repository userTest2Repository;

    @Test
    public void testSave() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userTest1Repository.save(new User("aa", "aa123456","aa@126.com", "aa",  formattedDate));
        userTest1Repository.save(new User("bb", "bb123456","bb@126.com", "bb",  formattedDate));
        userTest2Repository.save(new User("cc", "cc123456","cc@126.com", "cc",  formattedDate));
    }

    @Test
    public void testDelete() {
        userTest1Repository.deleteAll();
        userTest2Repository.deleteAll();
    }*/
}
