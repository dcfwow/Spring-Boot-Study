package com.zys.day132.mapper;

import com.zys.day132.enums.UserSexEnum;
import com.zys.day132.mapper.test1.User1Mapper;
import com.zys.day132.mapper.test2.User2Mapper;
import com.zys.day132.model.User;
import com.zys.day132.param.UserParam;
import com.zys.day132.result.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    //单数据源测试
/*    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() throws Exception {
        userMapper.insert(new User("aa", "a123456", UserSexEnum.MAN));
        userMapper.insert(new User("bb", "b123456", UserSexEnum.WOMAN));
        userMapper.insert(new User("cc", "b123456", UserSexEnum.WOMAN));

//		Assert.assertEquals(3, userMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
//		List<User> users = userMapper.getAll();
//		List<User> users = userMapper.getListByUserSex("MAN");
        Map param = new HashMap();
        param.put("username","aa");
        param.put("user_sex","MAN");
        List<User> users = userMapper.getListByNameAndSex(param);

        System.out.println("testQuery:"+users.toString());
    }


    @Test
    public void testUpdate() throws Exception {
        User user = userMapper.getOne(28L);
        System.out.println("user :"+user.toString());
        user.setNickName("it");
        user.setUserName("youknow");
//		userMapper.update(user);
        userMapper.updateUser(user);
//		Assert.assertTrue(("neo".equals(user.getNickName())));
    }


    @Test
    public void testPage() {
        UserParam userParam=new UserParam();
        userParam.setUserSex("WOMAN");
//		userParam.setUserName("neo");
        userParam.setCurrentPage(0);
        List<User> users=userMapper.getList(userParam);
        long count=userMapper.getCount(userParam);
        Page page = new Page(userParam,count,users);
        System.out.println("page === "+page);
    }*/

    //多数据源测试
    @Autowired
    private User1Mapper user1Mapper;
    @Autowired
    private User2Mapper user2Mapper;

    @Test
    public void testInsert() throws Exception {
        user1Mapper.insert(new User("aa111", "a123456", UserSexEnum.MAN));
        user1Mapper.insert(new User("bb111", "b123456", UserSexEnum.WOMAN));
        user2Mapper.insert(new User("cc222", "b123456", UserSexEnum.MAN));
    }
}
