package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * ユーザーログイン
 */
@Data
public class UpdateUsersResponse implements Serializable {

    private int updateuserid;
    private String updateUsername;

}
