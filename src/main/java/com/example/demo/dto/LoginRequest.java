package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * ユーザーログイン
 */
@Data
public class LoginRequest implements Serializable {
	
    private int userid;
    private String password;
    
}
