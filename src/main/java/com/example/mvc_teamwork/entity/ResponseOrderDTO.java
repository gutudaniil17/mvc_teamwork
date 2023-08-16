package com.example.mvc_teamwork.entity;

import lombok.*;

@Getter
@Setter
public class ResponseOrderDTO {
    private float amount;
    private int invoiceNumber;
    private String date;
    private String OrderDescription;
    private int orderId;

}
