package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LoginMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.User;

@Service
public class UserService {

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private UserMapper userMapper;

	public User loginAuthenticate(LoginRequest loginRequest) {
		// TODO 自動生成されたメソッド・スタブ
		User user = loginMapper.getAuthenticateResult(loginRequest);
		return user;
	}

	public void createUser(User user) {
		// TODO 自動生成されたメソッド・スタブ
		userMapper.insertUser(user);
	}

	public boolean isUserExists(User user) {
		// TODO 自動生成されたメソッド・スタブ
		int count = userMapper.isUserExists(user);
		return count>0;
	}

	public List<User> getUsers() {
		// TODO 自動生成されたメソッド・スタブ
		List<User> users = userMapper.getUsers();
		return users;
	}

	public void editUser(User user) {
		// TODO 自動生成されたメソッド・スタブ
		userMapper.updateUser(user);
	}

	public void deleteUser(Integer userid) {
		// TODO 自動生成されたメソッド・スタブ
		userMapper.deleteUser(userid);
	}

}
