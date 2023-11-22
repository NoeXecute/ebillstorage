package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.User;

@Mapper
public interface LoginMapper {

	public User getAuthenticateResult(LoginRequest loginRequest);
	
}