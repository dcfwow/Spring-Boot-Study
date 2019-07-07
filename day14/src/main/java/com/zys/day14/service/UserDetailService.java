package com.zys.day14.service;

import com.zys.day14.model.UserDetail;
import com.zys.day14.param.UserDetailParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDetailService {
    Page<UserDetail> findByCondition(UserDetailParam detailParam, Pageable pageable);
}
