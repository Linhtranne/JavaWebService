package com.data.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionDTO {
    private UUID senderId;
    private UUID receiverId;
    private Double money;
    private String note;
    private String status;
    private LocalDateTime createdAt;
}