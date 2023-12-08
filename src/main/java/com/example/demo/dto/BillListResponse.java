package com.example.demo.dto;

import java.io.Serializable;
import javax.persistence.Column;
import java.util.List;

import com.example.demo.entity.BillInfo;

import lombok.Data;

/**
 * ユーザーログイン
 */
@Data
public class BillListResponse implements Serializable {

    private List<BillInfo> billList;

    @Column(name = "total_bill")
    private int totalBill ;

}
