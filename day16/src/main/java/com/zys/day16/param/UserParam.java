package com.zys.day16.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * 接收添加用户的请求参数，并对参数做校验
 */
@Data
public class UserParam {
    private long id;
    @NotEmpty(message = "姓名不能为空")
    private String userName;
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6,message = "密码长度不能小于6位")
    private String passWord;
    @Min(value = 18,message = "必须年满18岁")
    @Max(value = 100,message = "年龄不能大于100岁")
    private int age;
}
