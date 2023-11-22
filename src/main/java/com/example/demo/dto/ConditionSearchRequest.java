package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.entity.Condition;

import lombok.Data;

/**
 * ユーザーログイン
 */
@Data
public class ConditionSearchRequest implements Serializable {

	private Condition condition;
    
    private int currentPage;
    
    private int pagesize;
    
    private int pageResult;
    
    private String prop;
    
    private String order;

}
