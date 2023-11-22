package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

import lombok.Data;

@Data
@Table(name = "bill_type")
public class BillType {

	@Column(name = "bill_typeno")
	private int billTypeno;

	@Column(name = "bill_type")
	private String billType;

	// @Column(name = "createymd")
	private Date createymd;

	// @Column(name = "createuserid")
	private int createuserid;

	// @Column(name = "updateymd")
	private Date updateymd;

	// @Column(name = "updateuserid")
	private int updateuserid;

}
