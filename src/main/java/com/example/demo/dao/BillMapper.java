package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.ConditionSearchRequest;
import com.example.demo.dto.CreateBillsRequest;
import com.example.demo.dto.GetBillsRequest;
import com.example.demo.dto.UpdateUsersResponse;
import com.example.demo.entity.BillDetails;
import com.example.demo.entity.BillInfo;
import com.example.demo.entity.Review;
import com.example.demo.entity.Searchmanage;
import com.example.demo.entity.Searchmatchmanage;
import com.example.demo.entity.User;

@Mapper
public interface BillMapper {

	public List<BillInfo> getBills(GetBillsRequest getBillsRequest);

	public List<BillInfo> conditionSearch(ConditionSearchRequest conditionSearchRequest);

	public List<BillInfo> getEditWaitBill(ConditionSearchRequest conditionSearchRequest);

	public List<BillInfo> getOnSubmit(GetBillsRequest getBillsRequest);

	public List<BillInfo> downloadBill(List<String> billnos);

	public Review getBillStatus(String billnos);

	public List<BillDetails> getNewBillDetails(String billno);

	public List<BillDetails> getBillDetails(String billno);

	public void agreeBill(String billno);

	public void disagreeBill(String billno);

	// public void createBill(CreateBillsRequest createBillsRequest);

	public void insertBillInput(CreateBillsRequest createBillsRequest);

	public void insertReview(CreateBillsRequest createBillsRequest);

	public void insertBillFile(CreateBillsRequest createBillsRequest);

	public void batchUpdateFileTemplog(CreateBillsRequest createBillsRequest);

	public void editBill(BillInfo editBillInfo);

	public List<User> getUpdateUserIds();

	public void giveEditPermissons(String billno);

	public void applyEditPermissons(String billno);

	public void deleteTempFile(String imageUrl);

	public List<UpdateUsersResponse> getUpdateUserids();

	public List<Searchmanage> getSearchmanage();

	public Searchmatchmanage getSearchmatchmanageByUser(int userid);

	public void insertFileTemplog(String uploadedFilePath);

}