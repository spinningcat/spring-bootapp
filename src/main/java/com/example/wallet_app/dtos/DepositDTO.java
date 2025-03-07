package com.example.wallet_app.dtos;

public class DepositDTO {
    private Long walletId; // Add this field
    private Double amount;
    private String source; // Assuming this is the source of the deposit

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}