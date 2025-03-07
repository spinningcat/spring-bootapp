package com.example.wallet_app.dtos;

import com.example.wallet_app.enums.TransactionStatus;

public class ApproveTransactionDTO {
    private Long transactionId;
    private TransactionStatus status;

    // Getters and setters
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}