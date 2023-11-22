package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BillMapper;
import com.example.demo.dto.ConditionSearchRequest;
import com.example.demo.dto.CreateBillsRequest;
import com.example.demo.dto.GetBillsRequest;
import com.example.demo.dto.UpdateUsersResponse;
import com.example.demo.entity.BillDetails;
import com.example.demo.entity.BillInfo;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;

@Service
public class BillService {

	@Autowired
	private BillMapper billMapper;

	public List<BillInfo> getBills(GetBillsRequest getBillsRequest) {
		// TODO 自動生成されたメソッド・スタブ
		List<BillInfo> bills = billMapper.getBills(getBillsRequest);
		return bills;
	}

	public List<BillInfo> conditionSearch(ConditionSearchRequest conditionSearchRequest) {
		// TODO 自動生成されたメソッド・スタブ
		List<BillInfo> billInfos = billMapper.conditionSearch(conditionSearchRequest);
		return billInfos;
	}

	public List<BillInfo> getEditWaitBill(ConditionSearchRequest conditionSearchRequest) {
		// TODO 自動生成されたメソッド・スタブ
		List<BillInfo> billInfos = billMapper.getEditWaitBill(conditionSearchRequest);
		return billInfos;
	}

	// public BillListResponse getEditWaitBill(ConditionSearchRequest
	// conditionSearchRequest) {
	// // TODO 自動生成されたメソッド・スタブ
	// List<BillInfo> billInfos =
	// billMapper.getEditWaitBill(conditionSearchRequest);

	// BillListResponse billListResponse = new BillListResponse();
	// billListResponse.setBills(billInfos);

	// int totalBill = 0;
	// if (billInfos != null && !billInfos.isEmpty()) {
	// totalBill = billInfos.get(0).getTotalBill();
	// }
	// billListResponse.setTotalBill(totalBill);

	// return billListResponse;
	// }

	public List<BillInfo> getOnSubmit(GetBillsRequest getBillsRequest) {
		// TODO 自動生成されたメソッド・スタブ
		List<BillInfo> billInfos = billMapper.getOnSubmit(getBillsRequest);
		return billInfos;
	}

	public List<BillInfo> downloadBill(List<String> billnos) {
		// TODO 自動生成されたメソッド・スタブ
		List<BillInfo> billInfos = billMapper.downloadBill(billnos);
		return billInfos;
	}

	public List<BillDetails> getBillDetails(String billno) {
		// TODO 自動生成されたメソッド・スタブ
		// Review review = billMapper.getBillStatus(billno);
		// if (review.getReviewStatus() == 1) {
		// 	List<BillDetails> billDetails = billMapper.getNewBillDetails(billno);
		// 	return billDetails;
		// }

		List<BillDetails> billDetails = billMapper.getBillDetails(billno);
		return billDetails;
	}

	public void agreeBill(String billno) {
		// TODO 自動生成されたメソッド・スタブ
		billMapper.agreeBill(billno);
	}

	public void disagreeBill(String billno) {
		// TODO 自動生成されたメソッド・スタブ
		billMapper.disagreeBill(billno);
	}

	@Transactional
	public void createBill(CreateBillsRequest createBillsRequest) {
		// TODO 自動生成されたメソッド・スタブ
		Date currentTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeAsString = dateFormat.format(currentTime);
		String billnoString = timeAsString +"BY"+createBillsRequest.getUpdateuserid()+"-AA";
		createBillsRequest.setBillno(billnoString);

		// SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		// String timeAsString1 =
		// dateFormat1.format(createBillsRequest.getTransactionymd());
		// createBillsRequest.setTransactionymd1(timeAsString1);

		try {
			// 插入数据到 bill_input 表
			billMapper.insertBillInput(createBillsRequest);

			// 插入数据到 review 表
			billMapper.insertReview(createBillsRequest);

			// 插入数据到 bill_file 表
			billMapper.insertBillFile(createBillsRequest);

			// 如果没有抛出异常，事务会在方法结束时自动提交
		} catch (Exception e) {
			// 如果发生异常，事务会回滚
			throw new RuntimeException("创建账单失败", e);
		}

		// billMapper.createBill(createBillsRequest);
	}

	// public String updateFile(MultipartFile multipartFile) {
	// // TODO 自動生成されたメソッド・スタブ
	// String imageUrl = billMapper.updateFile();
	// return imageUrl;
	// }

	public void editBill(BillInfo editBillInfo) {
		// TODO 自動生成されたメソッド・スタブ
		billMapper.editBill(editBillInfo);
	}

	public List<User> getUpdateUserIds() {
		// TODO 自動生成されたメソッド・スタブ
		List<User> updateUsers = billMapper.getUpdateUserIds();
		return updateUsers;
	}

	public void giveEditPermissons(String billno) {
		// TODO 自動生成されたメソッド・スタブ
		billMapper.giveEditPermissons(billno);
	}

	public void applyEditPermissons(String billno) {
		// TODO 自動生成されたメソッド・スタブ
		billMapper.applyEditPermissons(billno);
	}

	public List<UpdateUsersResponse> getUpdateUserids() {
		// TODO 自動生成されたメソッド・スタブ
		List<UpdateUsersResponse> updateUserids = billMapper.getUpdateUserids();
		return updateUserids;
	}

}
