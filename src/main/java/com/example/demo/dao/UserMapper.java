package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.User;

@Mapper
public interface UserMapper {

	public void insertUser(User user);

	public int isUserExists(User user);

	public List<User> getUsers();

	public void updateUser(User user);

	public void deleteUser(Integer userid);
}