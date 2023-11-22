package com.example.demo.entity;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data

public class Condition {
	
	private String transactionDateFrom;
	
	private String transactionDateTo;
	
	private int transactionAmountFrom;
	
	private int transactionAmountTo;
	
	private int customerno;
	
	private int billTypeno;
	
	private int updateuserid;
}
