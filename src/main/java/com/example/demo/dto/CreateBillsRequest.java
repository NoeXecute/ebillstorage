package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * ユーザーログイン
 */
@Data
public class CreateBillsRequest implements Serializable {

    private String billno;

    private int updateuserid;

    private int billTypeno;

    private int customerno;

    private String remark;

    private int transactionAmount;

    private Date  transactionymd;

    private boolean receiptIssuance;

    private String imageUrl;

    private List<String> imageUrls;
}
