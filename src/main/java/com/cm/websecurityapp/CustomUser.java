package com.cm.websecurityapp;

import com.cm.websecurityapp.entity.UserInfo;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {
    private static final long serialVersionUID = 1L;
    private UserInfo userInfo;
//    private
    public CustomUser(UserEntity user) {
        super(user.getUsername(), user.getPassword(), user.getGrantedAuthoritiesList());
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(user.getUsername());
        userInfo.setIdNo(user.getPassword());
        userInfo.setMobilephone(user.getPhoto());
        this.userInfo=userInfo;
    }
}
