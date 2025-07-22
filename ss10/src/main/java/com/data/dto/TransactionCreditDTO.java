package com.data.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TransactionCreditDTO {
    private UUID receiverId;
    private UUID creditCardId;
    private String note;
    private Double money;
}
