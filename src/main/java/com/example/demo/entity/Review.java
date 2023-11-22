package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "customer")
public class Review {

    @Column(name = "billno")
    private String billno;

    @Column(name = "review_status")
    private int reviewStatus;

    @Column(name = "edit_permissions")
    private boolean editPermissions;

}
