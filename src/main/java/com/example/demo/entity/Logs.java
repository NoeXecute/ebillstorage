package com.example.demo.entity;
import java.util.Date;

import lombok.Data;

@Data

public class Logs {
	
	private int historyno;
	
	private Date transactionymd;
	
	private int transactionAmount;
	
	private int customerno;
	
	private String customer;
	
	private int billTypeno;
	
	private String billType;
	
	private Date updateymd;
	
	private int updateuserid;
	
	private String updateUsername;
	
	private String remark;
}
