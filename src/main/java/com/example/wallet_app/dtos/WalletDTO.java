package com.example.wallet_app.dtos;

import com.example.wallet_app.enums.WalletCurrency;
import com.example.wallet_app.models.Wallet;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields from JSON
public class WalletDTO {

    private Long id;

    @NotNull(message = "User ID is required")
    @JsonProperty("userId") // Customize JSON field name if needed
    private Long userId;

    @NotEmpty(message = "Wallet name is required")
    private String walletName;

    @NotEmpty(message = "Owner name is required")
    private String ownerName;


    private WalletCurrency currency;

    @Min(value = 0, message = "Balance must be greater than or equal to 0")
    private Double balance;

    @Min(value = 0, message = "Usable balance must be greater than or equal to 0")
    private Double usableBalance;

    private boolean activeForShopping;
    private boolean activeForWithdrawal;

    // No-arg constructor
    public WalletDTO() {}

    // Constructor with fields
    public WalletDTO(Long id, Long userId, String walletName, String ownerName, WalletCurrency currency, Double balance, Double usableBalance, boolean activeForShopping, boolean activeForWithdrawal) {
        this.id = id;
        this.userId = userId;
        this.walletName = walletName;
        this.ownerName = ownerName;
        this.currency = currency;
        this.balance = balance;
        this.usableBalance = usableBalance;
        this.activeForShopping = activeForShopping;
        this.activeForWithdrawal = activeForWithdrawal;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public WalletCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(WalletCurrency currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getUsableBalance() {
        return usableBalance;
    }

    public void setUsableBalance(Double usableBalance) {
        this.usableBalance = usableBalance;
    }

    public boolean isActiveForShopping() {
        return activeForShopping;
    }

    public void setActiveForShopping(boolean activeForShopping) {
        this.activeForShopping = activeForShopping;
    }

    public boolean isActiveForWithdrawal() {
        return activeForWithdrawal;
    }

    public void setActiveForWithdrawal(boolean activeForWithdrawal) {
        this.activeForWithdrawal = activeForWithdrawal;
    }

    @Override
    public String toString() {
        return "WalletDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", walletName='" + walletName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                ", usableBalance=" + usableBalance +
                ", activeForShopping=" + activeForShopping +
                ", activeForWithdrawal=" + activeForWithdrawal +
                '}';
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long userId;
        private String walletName;
        private String ownerName;
        private WalletCurrency currency;
        private Double balance;
        private Double usableBalance;
        private boolean activeForShopping;
        private boolean activeForWithdrawal;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder walletName(String walletName) {
            this.walletName = walletName;
            return this;
        }

        public Builder ownerName(String ownerName) {
            this.ownerName = ownerName;
            return this;
        }

        public Builder currency(WalletCurrency currency) {
            this.currency = currency;
            return this;
        }

        public Builder balance(Double balance) {
            this.balance = balance;
            return this;
        }

        public Builder usableBalance(Double usableBalance) {
            this.usableBalance = usableBalance;
            return this;
        }

        public Builder activeForShopping(boolean activeForShopping) {
            this.activeForShopping = activeForShopping;
            return this;
        }

        public Builder activeForWithdrawal(boolean activeForWithdrawal) {
            this.activeForWithdrawal = activeForWithdrawal;
            return this;
        }

        public WalletDTO build() {
            return new WalletDTO(id, userId, walletName, ownerName, currency, balance, usableBalance, activeForShopping, activeForWithdrawal);
        }
    }
}