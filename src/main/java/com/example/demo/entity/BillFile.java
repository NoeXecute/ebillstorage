package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

import lombok.Data;

@Data
@Table(name = "bill_file")
public class BillFile {

    private String billno;

    @Column(name = "bill_filelink")
    private String billFileLink;

    private int fileno;

     @Column(name = "file_type")
    private String fileType;

}
