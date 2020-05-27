package com.cm.websecurityapp;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cm.ge.
 */
@RestController
public class UserController {
    @RequestMapping(value = "/user")
    public JSONObject user(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Authentication  authenticationImpl = new SecurityContextImpl().getAuthentication();
//        OAuth2Authentication
        authentication.getDetails();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName",authentication.getPrincipal());
        return jsonObject;
    }
}
