package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
/**
 * ユーザー情報 Controller
 */
@RestController
@CrossOrigin
@Validated
@RequestMapping("/")
public class LoginController {
	
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public Result login(@RequestBody @Valid LoginRequest loginRequest){
        if(userService.loginAuthenticate(loginRequest)!=null) {
        	User user = userService.loginAuthenticate(loginRequest);
        	return Result.ok(user);
        }else {
        	return Result.error("用户名或密码错误");
        }
    }
}
