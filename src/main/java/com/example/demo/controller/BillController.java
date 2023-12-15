package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.HashMap;


import javax.validation.Valid;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.FtpConfig;
import com.example.demo.service.BillService;

/**
 * カテゴリー処理 Controller
 */
@RestController
@CrossOrigin
@Validated
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private FtpConfig ftpConfig;

    // private static final String imageUrl="ftp://192.168.11.9/taojun-test/";
    // private static final String imageUrl = "D:/ftp/nixiao-test/";
    private static final String imageUrl = "nixiao-test";

    @RequestMapping(value = "/getBills", method = RequestMethod.POST)
    public Result GetBills(@RequestBody @Valid GetBillsRequest getBillsRequest) {
        getBillsRequest.setPageResult((getBillsRequest.getCurrentPage() - 1) * getBillsRequest.getPagesize());
        List<BillInfo> bills = billService.getBills(getBillsRequest);
        return Result.ok(bills);
    }

    @RequestMapping(value = "/conditionSearch", method = RequestMethod.POST)
    public Result ConditionSearch(@RequestBody @Valid ConditionSearchRequest conditionSearchRequest) {
        conditionSearchRequest
                .setPageResult((conditionSearchRequest.getCurrentPage() - 1) * conditionSearchRequest.getPagesize());
//        List<BillInfo> billInfos = billService.conditionSearch(conditionSearchRequest);
    //
        BillListResponse billListResponse = billService.conditionSearch(conditionSearchRequest);
        return Result.ok(billListResponse);
    }

    @RequestMapping(value = "/getEditWaitBill", method = RequestMethod.POST)
    public Result GetEditWaitBill(@RequestBody @Valid ConditionSearchRequest conditionSearchRequest) {
        conditionSearchRequest
                .setPageResult((conditionSearchRequest.getCurrentPage() - 1) * conditionSearchRequest.getPagesize());
        List<BillInfo> billInfos = billService.getEditWaitBill(conditionSearchRequest);
        return Result.ok(billInfos);
    }

    @RequestMapping(value = "/getOnSubmit", method = RequestMethod.POST)
    public Result GetOnSubmit(@RequestBody @Valid GetBillsRequest getBillsRequest) {
        List<BillInfo> billInfos = billService.getOnSubmit(getBillsRequest);
        return Result.ok(billInfos);
    }

    @RequestMapping(value = "/getBillDetails/{billno}", method = RequestMethod.GET)
    public Result GetBillDetails(@RequestBody @PathVariable String billno) {
        System.out.println("billno" + billno);
        List<BillDetails> billDetails = billService.getBillDetails(billno);
        return Result.ok(billDetails);
    }

    @RequestMapping(value = "/agreeBill/{billno}", method = RequestMethod.GET)
    public Result AgreeBill(@RequestBody @PathVariable String billno) {
        billService.agreeBill(billno);
        return Result.ok(billno);
    }

    @RequestMapping(value = "/disagreeBill/{billno}", method = RequestMethod.GET)
    public Result DisgreeBill(@RequestBody @PathVariable String billno) {
        billService.disagreeBill(billno);
        return Result.ok(billno);
    }

    @RequestMapping(value = "/createBill", method = RequestMethod.POST)
    public Result CreateBill(@RequestBody  CreateBillsRequest createBillsRequest) {
        System.out.println("================"+createBillsRequest);
        billService.createBill(createBillsRequest);
        return Result.ok(null);
    }

    @RequestMapping(value = "/updateFile", method = RequestMethod.POST)
    public Result UpdateFile(@RequestPart(value = "file") @Valid MultipartFile multipartFile,
            @RequestParam(value = "userid") int userId)
            throws SocketException, IOException {
        if (multipartFile.isEmpty()) {
            return Result.error("Error: File is empty");
        }

        try {
            // 获取文件名
            String fileName = multipartFile.getOriginalFilename();
            System.out.println("fileName:" + fileName);
            if (fileName == null) {
                return Result.error("Error: ファイル名が空です。アップロードできません。");
            }
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
            String dir = dateFormat.format(date);
            // 保存文件到服务器，你可以根据需要更改存储路径
//            String uploadDir = "D:/ftp/nixiao-test/" + dir;
            String uploadDir = "/Users/mucc/Documents/test/" + dir;
            File uploadPath = new File(uploadDir);

            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            SimpleDateFormat dateFormatForName = new SimpleDateFormat("yyyyMMddHHmmss");
            // String fileExtension = fileName.substring(fileName.lastIndexOf(".")); //
            // 获取文件扩展名
            String newFileName = dateFormatForName.format(new Date()) + "_" + userId + "_" + fileName;
            File dest = new File(uploadPath + File.separator + newFileName);
            multipartFile.transferTo(dest);

//            String uploadedFilePath = "http://192.168.11.9:8091/" + dir + "/" + newFileName;
            String uploadedFilePath = "http://192.168.11.31:8000/" + dir + "/" + newFileName;
            System.out.println("文件上传成功。路径：" + uploadedFilePath);
            billService.insertFileTemplog(uploadedFilePath);

            // 可以返回成功的响应
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("imageUrl", uploadedFilePath);
            return Result.ok(responseMap);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("Error: " + e.getMessage());

        }
    }

    @RequestMapping(value = "/editBill", method = RequestMethod.POST)
    public Result EditBill(@RequestBody @Valid BillInfo editBillInfo) {
        billService.editBill(editBillInfo);
        return Result.ok(null);
    }

    @RequestMapping(value = "/getUpdateUserIds", method = RequestMethod.GET)
    public Result GetUpdateUserIds() {
        List<User> updateUsers = billService.getUpdateUserIds();
        return Result.ok(updateUsers);
    }

    @RequestMapping(value = "/giveEditPermissons/{billno}", method = RequestMethod.GET)
    public Result GiveEditPermissons(@RequestBody @PathVariable String billno) {
        billService.giveEditPermissons(billno);
        return Result.ok(null);
    }

    @RequestMapping(value = "/applyEditPermissons/{billno}", method = RequestMethod.GET)
    public Result ApplyEditPermissons(@RequestBody @PathVariable String billno) {
        billService.applyEditPermissons(billno);
        return Result.ok(null);
    }

    @RequestMapping(value = "/downloadBill", method = RequestMethod.POST)
    public Result DownloadBill(@RequestBody @Valid List<String> billnos) {
        List<BillFile> billInfos = billService.downloadBill(billnos);
//        Map<String, String> billInfos = billService.downloadBill(billnos);
        return Result.ok(billInfos);
    }

    @RequestMapping(value = "/getUpdateUserids", method = RequestMethod.GET)
    public Result GetUpdateUserids() {
        List<UpdateUsersResponse> updateUserids = billService.getUpdateUserids();
        return Result.ok(updateUserids);
    }
//?????
    @RequestMapping(value = "/getSearchmanage/{userid}", method = RequestMethod.GET)
    public Result GetSearchmanage(@RequestBody @PathVariable int userid) {
        List<Searchmanage> searchmanageList = billService.getSearchmanage();
        Searchmatchmanage searchmatchmanage = billService.getSearchmatchmanageByUser(userid);
        if (searchmatchmanage == null) {
            return Result.error("Error: 現在のユーザーが見つかりませんでした");
        }
        searchmanageList = billService.searchmanageDeal(searchmanageList, searchmatchmanage);

        return Result.ok(searchmanageList);
    }

    @RequestMapping(value = "/deleteTempFile", method = RequestMethod.POST)
    public Result DeleteTempFile(@RequestBody Map<String, Object> imageUrlMap) {
        billService.deleteTempFile(imageUrlMap);
        return Result.ok(null);
    }




}
