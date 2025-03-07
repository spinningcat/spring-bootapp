package com.example.wallet_app.dtos;

public class WithdrawDTO {
    private Long walletId; // Add this field
    private Double amount;
    private String destination; // Assuming this is the destination of the withdrawal

    // Getters and setters
    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}