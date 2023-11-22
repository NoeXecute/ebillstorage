package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;

import lombok.Data;

@Data

public class BillDetails {

	private String billno;

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

	private String imageUrl;

	private int reviewStatus;

	private boolean editPermissions;

}
