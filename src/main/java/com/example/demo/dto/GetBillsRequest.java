package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * ユーザーログイン
 */
@Data
public class GetBillsRequest implements Serializable {
	
    private int currentPage;
    
    private int pagesize;
    
    private int pageResult;
    
    private String prop;
    
    private String order;

}
