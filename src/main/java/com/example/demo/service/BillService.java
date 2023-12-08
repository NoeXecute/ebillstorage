package com.example.demo.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.demo.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BillMapper;
import com.example.demo.entity.BillDetails;
import com.example.demo.entity.BillInfo;
import com.example.demo.entity.Result;
import com.example.demo.entity.Review;
import com.example.demo.entity.Searchmanage;
import com.example.demo.entity.Searchmatchmanage;
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

	public BillListResponse conditionSearch(ConditionSearchRequest conditionSearchRequest) {
		// TODO 自動生成されたメソッド・スタブ
//		List<BillInfo> billInfos = billMapper.conditionSearch(conditionSearchRequest);
		if (conditionSearchRequest.getReviewStatus() == 1){
			BillListResponse billListResponse=new BillListResponse();
			billListResponse.setBillList(billMapper.waitingApproval(conditionSearchRequest));
			billListResponse.setTotalBill(billMapper.waitingApprovalTotalNum(conditionSearchRequest));

			return billListResponse;
		} else {
			BillListResponse billListResponse=new BillListResponse();
			billListResponse.setBillList(billMapper.conditionSearch(conditionSearchRequest));
			billListResponse.setTotalBill(billMapper.conditionSearchTotalNum(conditionSearchRequest));
			return billListResponse;
		}
//		total返回
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
		// List<BillDetails> billDetails = billMapper.getNewBillDetails(billno);
		// return billDetails;
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
		String billnoString = timeAsString + "BY" + createBillsRequest.getUpdateuserid() + "-AA";
		createBillsRequest.setBillno(billnoString);


		try {
			// 插入数据到 bill_input 表
			billMapper.insertBillInput(createBillsRequest);

			// 插入数据到 review 表
			billMapper.insertReview(createBillsRequest);

			// 插入数据到 bill_file 表
			billMapper.insertBillFile(createBillsRequest);

			billMapper.batchUpdateFileTemplog(createBillsRequest);
			// 如果没有抛出异常，事务会在方法结束时自动提交
		} catch (Exception e) {
			// 如果发生异常，事务会回滚
			throw new RuntimeException("创建账单失败", e);
		}

	}


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

	public void deleteTempFile(Map<String, Object> imageUrlMap) {
		String imageUrl = (String) imageUrlMap.get("imageUrl");
		String localFilePath = convertUrlToPath(imageUrl);
		// System.out.println(localFilePath);

		// 构建文件对象
		File fileToDelete = new File(localFilePath);

		// 检查文件是否存在，然后进行删除
		if (fileToDelete.exists()) {
			if (fileToDelete.delete()) {
				billMapper.deleteTempFile(imageUrl);
			} else {
				throw new RuntimeException("Failed to delete file");
			}
		} else {
			throw new RuntimeException("File not found");
		}
	}

	public static String convertUrlToPath(String imageUrl) {
		try {
			URL url = new URL(imageUrl);
			Path path = Paths.get("D:/ftp/nixiao-test", url.getPath());

			// 获取规范化的路径字符串
			return path.normalize().toString();
		} catch (Exception e) {
			// 处理 URL 格式错误的异常
			e.printStackTrace();
			throw new RuntimeException("Handling URL Format Errors Exception.", e);
		}
	}

	public List<Searchmanage> getSearchmanage() {
		List<Searchmanage> searchmanageList = billMapper.getSearchmanage();
		return searchmanageList;
	}

	public Searchmatchmanage getSearchmatchmanageByUser(int userid) {
		Searchmatchmanage searchmatchmanage = billMapper.getSearchmatchmanageByUser(userid);
		return searchmatchmanage;
	}

	public List<Searchmanage> searchmanageDeal(List<Searchmanage> searchmanageList,
			Searchmatchmanage searchmatchmanage) {
		Iterator<Searchmanage> iterator = searchmanageList.iterator();
		while (iterator.hasNext()) {
			Searchmanage item = iterator.next();

			// 根据Searchmatchmanage对象的属性值判断是否要移除该项
			if (!matchSearchStatus(item, searchmatchmanage)) {
				iterator.remove();
			}
		}

		return searchmanageList;
	}

	private static boolean matchSearchStatus(Searchmanage item, Searchmatchmanage searchmatchmanage) {
		String condition = (String) item.getCondition();
		if (!item.isSearchStatus()) {
			return false;
		}

		// 根据Searchmatchmanage对象的属性值判断是否要移除该项
		switch (condition) {
			case "pending_approval_new":
				return searchmatchmanage.isPendingApprovalNew();
			case "pending_approval_modification":
				return searchmatchmanage.isPendingApprovalModification();
			case "approved":
				return searchmatchmanage.isApproved();
			case "denied":
				return searchmatchmanage.isDenied();
			case "editing_permission_requested":
				return searchmatchmanage.isEditingPermissionRequested();
			case "editing_pending":
				return searchmatchmanage.isEditingPending();
			default:
				return false;
		}
	}

	public void insertFileTemplog(String uploadedFilePath) {
		billMapper.insertFileTemplog(uploadedFilePath);
	}

}
