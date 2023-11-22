package com.example.demo;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

@Configuration
public class FtpConfig extends DefaultFtpSessionFactory{
        public static FTPClient FtpConfig() {
        FtpConfig ftpConfig = new FtpConfig();
        ftpConfig.setHost("192.168.11.9");
        ftpConfig.setPort(21);
        FTPClient fTPClient = ftpConfig.createClientInstance();
        return fTPClient;
    }
}