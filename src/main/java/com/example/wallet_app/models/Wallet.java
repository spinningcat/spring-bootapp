package com.example.wallet_app.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id") // This establishes the relationship with the Users entity
    private Users user;

    private String walletName;
    private String ownerName;
    private String currency;
    private Double balance;
    private Double usableBalance;

    private boolean activeForShopping;
    private boolean activeForWithdrawal;

    // Getters and setters
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

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}