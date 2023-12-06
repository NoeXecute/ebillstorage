package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

/**
 * ユーザー処理 Controller
 */
@RestController
@CrossOrigin
@Validated
@RequestMapping("/user")
public class UserController {
	
    @Autowired
    private UserService userService;

    @RequestMapping(value="/createUser",method=RequestMethod.POST)
    public Result createUser(@RequestBody @Valid User user){
        if (userService.isUserExists(user)) {
            // 如果已存在相同的账单类型，则返回错误信息
            return Result.error("相同の請求タイプは既に存在しています、重複して追加できません。");
        }
        userService.createUser(user);
        return Result.ok(null);
    }
    
    @RequestMapping(value="/getUsers",method=RequestMethod.GET)
    public Result getUsers(){
    	List<User> users = userService.getUsers();
        return Result.ok(users);
    }
    
    @RequestMapping(value="/editUser",method=RequestMethod.POST)
    public Result editUser(@RequestBody @Valid User user){
        userService.editUser(user);
        return Result.ok(null);
    }

    @RequestMapping(value="/deleteUser/{userid}",method=RequestMethod.GET)
    public Result deleteUser(@RequestBody @Valid @PathVariable Integer userid){
        userService.deleteUser(userid);
        return Result.ok(null);
    }
    //++

}