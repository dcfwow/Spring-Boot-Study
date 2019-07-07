package com.zys.day131.mapper.two;

import com.zys.day131.model.User;
import com.zys.day131.param.UserParam;

import java.util.List;

public interface User2Mapper {
    List<User> getAll();
    User getOne(Long id);
    void insert(User user);
    int update(User user);
    int delete(Long id);
    List<User> getList(UserParam userParam);
    int getCount(UserParam userParam);
}
