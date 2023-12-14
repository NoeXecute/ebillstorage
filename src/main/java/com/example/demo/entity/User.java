package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import lombok.Data;

@Data
public class User implements Serializable {
    /**
     * ユーザー番号
     */
    @Column(name = "userid")
    private int userid;

    /**
     * ユーザー状態
     */
    @Column(name = "user_status")
    private boolean userStatus;

    /**
     * ユーザー名
     */
    @Column(name = "username")
    private String username;

    /**
     * ユーザー名
     */
    @Column(name = "password")
    private String password;

    /**
     * 権限番号
     */
    @Column(name = "rolesno")
    private int rolesno;

    /**
     * 更新ユーザー番号
     */
    @Column(name = "createuserid")
    private int createuserid;

    @Column(name = "createymd")
    private Date createymd;

    @Column(name = "updateuserid")
    private int updateuserid;

    @Column(name = "updateymd")
    private Date updateymd;

    @Column(name = "remark")
    private String remark;

    @Column(name = "search_permissions")
    private String searchPermissions;
}
