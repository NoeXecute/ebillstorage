package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

import lombok.Data;

@Data
@Table(name = "customer")
public class Customer {

	@Column(name = "customerno")
	private int customerno;

	@Column(name = "customer_type")
	private String customerType;

	@Column(name = "customer_typeno")
	private int customerTypeno;

	@Column(name = "customer")
	private String customer;

	@Column(name = "createymd")
	private Date createymd;

	@Column(name = "createuserid")
	private int createuserid;

	@Column(name = "updateymd")
	private Date updateymd;

	@Column(name = "updateuserid")
	private int updateuserid;

	@Column(name = "remark")
	private String remark;

	@Column(name = "address")
	private String address;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "legal_entity_number")
	private String legalEntityNumber;

}
