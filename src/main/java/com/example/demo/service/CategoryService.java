package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryMapper;
import com.example.demo.entity.BillType;
import com.example.demo.entity.Customer;
import com.example.demo.entity.CustomerType;
import com.example.demo.entity.User;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryMapper categoryMapper;
    
	public void SetPath(String pathAddress) {
		// TODO 自動生成されたメソッド・スタブ
		categoryMapper.setPath(pathAddress);
	}

	public void deleteBillType(int billTypeno) {
		// TODO 自動生成されたメソッド・スタブ
		categoryMapper.deleteBillType(billTypeno);
	}

	public void addBillType(BillType billType) {
		// TODO 自動生成されたメソッド・スタブ
		categoryMapper.insertBillType(billType);
	}

	public void editBillType(BillType billType) {
		// TODO 自動生成されたメソッド・スタブ
		categoryMapper.editBillType(billType);
	}

	public boolean isBillTypeExists(String billType) {
		 // 使用 categoryMapper（假设是 MyBatis 的 Mapper）查询数据库
		 int count = categoryMapper.isBillTypeExists(billType);

		 // 如果 count 大于 0，表示存在相同的账单类型
		 return count > 0;
	}

	public List<BillType> getBillTypes() {
		// TODO 自動生成されたメソッド・スタブ
		List<BillType> billTypes = categoryMapper.getBillTypes();
		return billTypes;
	}

	public List<Customer> getCustomers() {
		// TODO 自動生成されたメソッド・スタブ
		List<Customer> customers = categoryMapper.getCustomers();
		return customers;
	}

	public void deleteCustomer(Customer customer) {
		// TODO 自動生成されたメソッド・スタブ
		categoryMapper.deleteCustomer(customer);
	}

	public boolean isCustomerExists(Customer customer) {
		 int count = categoryMapper.isCustomerExists(customer);
		 return count > 0;
	}

	public void addCustomer(Customer customer) {
		// TODO 自動生成されたメソッド・スタブ
		if(categoryMapper.getCustomerTypeByName(customer.getCustomerType())==null) {
			categoryMapper.insertCustomerType(customer.getCustomerType());
		};
		categoryMapper.insertCustomer(customer);
	}

	public void editCustomer(Customer customer) {
		// TODO 自動生成されたメソッド・スタブ
		if(categoryMapper.getCustomerTypeByName(customer.getCustomerType())==null) {
			categoryMapper.insertCustomerType(customer.getCustomerType());
		};
		categoryMapper.editCustomer(customer);
	}
	
	
	public List<CustomerType> getCustomerTypes(){
		List<CustomerType> customerTypes = categoryMapper.getCustomerTypes();
		return customerTypes;
	};
}
