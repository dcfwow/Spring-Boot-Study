package com.zys.day131.mapper;

import com.zys.day131.enums.UserSexEnum;
import com.zys.day131.mapper.one.User1Mapper;
import com.zys.day131.mapper.two.User2Mapper;
import com.zys.day131.model.User;
import com.zys.day131.param.UserParam;
import com.zys.day131.result.Page;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private User1Mapper user1Mapper;
    @Autowired
    private User2Mapper user2Mapper;

    @Test
    public void testUser(){
        //新增
        user1Mapper.insert(new User("dcfwow","123456", UserSexEnum.MAN));
        //删除
        user1Mapper.delete(2l);
        User user = user1Mapper.getOne(1l);
        user.setNickName("wower");
        //修改
        user1Mapper.update(user);
        //查询
        List<User> users = user1Mapper.getAll();
    }

    @Test
    public void testInsert()  {
        user1Mapper.insert(new User("aa", "a123456", UserSexEnum.MAN));
        user1Mapper.insert(new User("bb", "b123456", UserSexEnum.WOMAN));
        user1Mapper.insert(new User("cc", "b123456", UserSexEnum.WOMAN));

        Assert.assertEquals(3, user1Mapper.getAll().size());
    }

    @Test
    public void testQuery() {
        List<User> users = user1Mapper.getAll();
        if(users==null || users.size()==0){
            System.out.println("is null");
        }else{
            System.out.println("users list is :"+users.toString());
        }
    }


    @Test
    public void testUpdate() {
        long id=1l;
        User user = user1Mapper.getOne(id);
        if(user!=null){
            System.out.println(user.toString());
            user.setNickName("neo");
            user1Mapper.update(user);
            Assert.assertTrue(("neo".equals(user1Mapper.getOne(id).getNickName())));
        }else {
            System.out.println("not find user id="+id);
        }
    }


    @Test
    public void testDelete() {
        int count=user1Mapper.delete(29l);
        if(count>0){
            System.out.println("delete is sucess");
        }else {
            System.out.println("delete if failed");
        }
    }

    @Test
    public void testPage(){
        UserParam userParam = new UserParam();
        userParam.setUserSex("WOMAN");
        userParam.setCurrentPage(1);
        List<User> users = user1Mapper.getList(userParam);
        long count = user1Mapper.getCount(userParam);
        Page page = new Page(userParam,count,users);
        System.out.println(page);
    }

    //测试多数据源插入
    @Test
    public void testMultiInsert() {
        user1Mapper.insert(new User("aa111", "a123456", UserSexEnum.MAN));
        user1Mapper.insert(new User("bb111", "b123456", UserSexEnum.WOMAN));
        user2Mapper.insert(new User("cc222", "b123456", UserSexEnum.MAN));
    }
}
