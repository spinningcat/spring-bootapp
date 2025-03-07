package com.example.wallet_app.services;

import com.example.wallet_app.dtos.WalletDTO;
import com.example.wallet_app.enums.TransactionStatus;
import com.example.wallet_app.enums.TransactionType;
import com.example.wallet_app.models.Transaction;
import com.example.wallet_app.models.Wallet;
import com.example.wallet_app.repositories.TransactionRepository;
import com.example.wallet_app.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    public Transaction deposit(WalletDTO walletDTO, Double amount, String source) {
        // Fetch the Wallet entity using the walletId from WalletDTO
        Wallet wallet = walletRepository.findById(walletDTO.getId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        // Create a new Transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setSource(source);
        transaction.setWallet(wallet); // Set the Wallet entity
        transaction.setType(TransactionType.DEPOSIT); // Set the transaction type
        transaction.setStatus(TransactionStatus.PENDING); // Set the initial status

        // Save the transaction
        return transactionRepository.save(transaction);
    }

    public Transaction withdraw(WalletDTO walletDTO, Double amount, String destination) {
        // Fetch the Wallet entity using the walletId from WalletDTO
        Wallet wallet = walletRepository.findById(walletDTO.getId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        // Create a new Transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDestination(destination);
        transaction.setWallet(wallet); // Set the Wallet entity
        transaction.setType(TransactionType.WITHDRAW); // Set the transaction type
        transaction.setStatus(TransactionStatus.PENDING); // Set the initial status

        // Save the transaction
        return transactionRepository.save(transaction);
    }

    public Transaction approveTransaction(Long transactionId, TransactionStatus status) {
        // Fetch the transaction
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        // Update the wallet balance based on the transaction type
        if (status == TransactionStatus.APPROVED) {
            Wallet wallet = transaction.getWallet();
            if (transaction.getType() == TransactionType.DEPOSIT) {
                wallet.setUsableBalance(wallet.getUsableBalance() + transaction.getAmount());
            } else if (transaction.getType() == TransactionType.WITHDRAW) {
                wallet.setBalance(wallet.getBalance() - transaction.getAmount());
            }
            walletRepository.save(wallet);
        }

        // Update the transaction status
        transaction.setStatus(status);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByWalletId(Long walletId) {
        return transactionRepository.findByWalletId(walletId);
    }
}