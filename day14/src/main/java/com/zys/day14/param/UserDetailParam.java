package com.zys.day14.param;

import lombok.Data;

@Data
public class UserDetailParam {
    private String userId;
    private Integer minAge;
    private Integer maxAge;
    private String realName;
    private String introduction;
    private String city;
}
