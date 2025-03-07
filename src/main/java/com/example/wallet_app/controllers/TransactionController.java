package com.example.wallet_app.controllers;

import com.example.wallet_app.dtos.*;
import com.example.wallet_app.models.Transaction;
import com.example.wallet_app.models.Wallet;
import com.example.wallet_app.services.TransactionService;
import com.example.wallet_app.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private WalletService walletService;



    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestBody DepositDTO depositDTO) {
        // Now depositDTO has getWalletId()
        WalletDTO wallet = walletService.getWalletById(depositDTO.getWalletId());
        Transaction transaction = transactionService.deposit(wallet, depositDTO.getAmount(), depositDTO.getSource());
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody WithdrawDTO withdrawDTO) {
        // Now withdrawDTO has getWalletId()
        WalletDTO wallet = walletService.getWalletById(withdrawDTO.getWalletId());
        Transaction transaction = transactionService.withdraw(wallet, withdrawDTO.getAmount(), withdrawDTO.getDestination());
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/approve")
    public ResponseEntity<Transaction> approveTransaction(@RequestBody ApproveTransactionDTO approveTransactionDTO) {
        Transaction transaction = transactionService.approveTransaction(
                approveTransactionDTO.getTransactionId(),
                approveTransactionDTO.getStatus()
        );
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/wallet/{walletId}")
    public ResponseEntity<List<Transaction>> getTransactionsByWalletId(@PathVariable Long walletId) {
        List<Transaction> transactions = transactionService.getTransactionsByWalletId(walletId);
        return ResponseEntity.ok(transactions);
    }
}