package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.*;
import org.apache.ibatis.annotations.Mapper;


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

	public List<Searchmanage> getSearchManage();
}