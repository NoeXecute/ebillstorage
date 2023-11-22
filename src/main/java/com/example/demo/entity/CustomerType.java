package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "customer")
public class CustomerType {
	
	@Column(name = "customer_typeno")
	private int customerTypeno;

	@Column(name = "customer_type")
	private String customerType;

}
