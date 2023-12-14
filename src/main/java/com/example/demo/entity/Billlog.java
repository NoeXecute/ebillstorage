package com.example.demo.entity;
import java.util.Date;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "bill_log")
public class Billlog {

	private String billno;

	private Date transactionymd;

	@Column(name = "transaction_amount")
	private int transactionAmount;

	private int customerno;

	@Column(name = "bill_typeno")
	private int billTypeno;

	private Date updateymd;

	private int updateuserid;

	private String remark;

	@Column(name = "send_receive_type")
	private int sendReceiveType;

	private int historyno;


}
