package com.zys.day131.model;

import com.zys.day131.enums.UserSexEnum;
import lombok.Data;
import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String passWord;
    private UserSexEnum userSex;
    private String nickName;

    public User(){

    }

    public User(String userName,String passWord,UserSexEnum userSex){
        this.userName = userName;
        this.passWord = passWord;
        this.userSex = userSex;
    }
}
