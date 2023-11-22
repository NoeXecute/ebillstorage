package com.example.demo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderNumberGenerator {
    private static final String PREFIX = "O";
    private static final String PREFIXITEM = "Oi";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final AtomicInteger SEQUENCE = new AtomicInteger(0);

    public static String generateOrderNumber(Integer tableId) {
    	String tableMark = "-"+tableId.toString();
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DATE_FORMATTER);
        int sequenceNumber = SEQUENCE.getAndIncrement();
        String paddedSequenceNumber = String.format("%04d", sequenceNumber);

        return PREFIX + timestamp + tableMark + paddedSequenceNumber;
    }
    
    public static String generateOrderNumberItem(Integer tableId) {
    	String tableMark = "-"+tableId.toString();
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DATE_FORMATTER);
        int sequenceNumber = SEQUENCE.getAndIncrement();
        String paddedSequenceNumber = String.format("%04d", sequenceNumber);

        return PREFIXITEM + timestamp + tableMark + paddedSequenceNumber;
    }
}
