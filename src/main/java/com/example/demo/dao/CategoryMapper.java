package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.BillType;
import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerType;
import com.example.demo.entity.User;


@Mapper
public interface CategoryMapper {

	public void setPath(String pathAddress);

	public void deleteBillType(int billTypeno);

	public void insertBillType(BillType billType);

	public void editBillType(BillType billType);

	public int isBillTypeExists(String billType);

	public List<BillType> getBillTypes();

	public List<Customer> getCustomers();
	
	public CustomerType getCustomerTypeByName(String CustomerType);

	public void deleteCustomer(Customer customerno);

	public void insertCustomer(Customer customer);

	public void editCustomer(Customer customer);

	public int isCustomerExists(Customer customer);

	public List<CustomerType> getCustomerTypes();
	
	public CustomerType getCustomerType();

	public void insertCustomerType(String customerTypeTemp);
}