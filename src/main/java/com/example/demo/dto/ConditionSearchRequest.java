package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

import com.example.demo.entity.Condition;

import lombok.Data;

/**
 * ユーザーログイン
 */
@Data
public class ConditionSearchRequest implements Serializable {

	// private Condition condition;

    private Date transactionDateFrom;
	
	private Date transactionDateTo;
	
	private int transactionAmountFrom;
	
	private int transactionAmountTo;
	
	private int customerno;
	
	private int billTypeno;
	
	private int updateuserid;
    
    private String prop;
    
    private String order;

    private int currentPage;
    
    private int pagesize;
    
    private int pageResult;
}
