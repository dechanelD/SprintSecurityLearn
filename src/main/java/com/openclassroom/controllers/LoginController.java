package com.openclassroom.controllers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class LoginController {

    @GetMapping("/user")
    public String getUser() {
        return "Welcome, User";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "Welcome, Admin";
    }

    @GetMapping("/")
    public String getUserInfo(Principal user){
        StringBuffer userInfo = new StringBuffer();
        if(user instanceof UsernamePasswordAuthenticationToken){
            userInfo.append(getUserNamePassWordUserInfo(user));
        }

        return userInfo.toString();
    }

    private StringBuffer getUserNamePassWordUserInfo( Principal user ){
        StringBuffer userNameInfo = new StringBuffer();
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)user;
        if(token.isAuthenticated()){
            //
            User u = (User)token.getPrincipal();
            userNameInfo.append( "Welcome, " + u.getUsername() );
        }else{
            userNameInfo.append( "NA");
        }
        return userNameInfo;

    }

}
