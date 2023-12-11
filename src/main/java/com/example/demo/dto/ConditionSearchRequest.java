package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

import com.example.demo.entity.Condition;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;

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

	private int reviewStatus;

	@Column(name = "send_receive_type")
	private int sendReceiveType;
    
    private String prop;
    
    private String order;

    private int currentPage;
    
    private int pagesize;
    
    private int pageResult;
}
