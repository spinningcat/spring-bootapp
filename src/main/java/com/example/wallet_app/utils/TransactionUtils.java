package com.example.wallet_app.utils;

import com.example.wallet_app.enums.TransactionStatus;
import com.example.wallet_app.models.Wallet;

public class TransactionUtils {

    public static void updateWalletBalance(Wallet wallet, Double amount, TransactionStatus status) {
        if (status == TransactionStatus.APPROVED) {
            wallet.setBalance(wallet.getBalance() + amount);
            wallet.setUsableBalance(wallet.getUsableBalance() + amount);
        } else if (status == TransactionStatus.PENDING) {
            wallet.setBalance(wallet.getBalance() + amount);
        }
    }
}