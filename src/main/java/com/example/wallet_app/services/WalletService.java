package com.example.wallet_app.services;

import com.example.wallet_app.dtos.WalletDTO;

import com.example.wallet_app.exceptions.UserNotFoundException;
import com.example.wallet_app.exceptions.WalletNotFoundException;
import com.example.wallet_app.models.Users;
import com.example.wallet_app.models.Wallet;
import com.example.wallet_app.repositories.UserRepository;
import com.example.wallet_app.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    // Get all wallets as DTOs
    public List<WalletDTO> getAllWallets() {
        List<Wallet> wallets = walletRepository.findAll();
        return wallets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Create a wallet from a DTO
    public WalletDTO createWallet(WalletDTO walletDTO) {
        Wallet wallet = convertToEntity(walletDTO);
        Wallet savedWallet = walletRepository.save(wallet);
        return convertToDTO(savedWallet);
    }

    // Get a wallet by ID as a DTO
    public WalletDTO getWalletById(Long id) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found with id: " + id));
        return convertToDTO(wallet); // Convert Wallet entity to WalletDTO
    }

    // Get wallets by user ID
    public List<WalletDTO> getWalletsByUserId(Long userId) {
        List<Wallet> wallets = walletRepository.findByUserId(userId);
        return wallets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Update a wallet from a DTO
    public WalletDTO updateWallet(WalletDTO walletDTO) {
        Wallet existingWallet = walletRepository.findById(walletDTO.getId())
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found with id: " + walletDTO.getId()));

        // Update fields from DTO
        existingWallet.setOwnerName(walletDTO.getOwnerName());

        // Handle currency (assuming Wallet uses String and WalletDTO uses a custom Currency class)
        if (walletDTO.getCurrency() != null) {
            existingWallet.setCurrency(walletDTO.getCurrency().toString()); // Adjust this based on your Currency class
        } else {
            throw new IllegalArgumentException("Currency cannot be null");
        }

        existingWallet.setBalance(walletDTO.getBalance());
        existingWallet.setUsableBalance(walletDTO.getUsableBalance());
        existingWallet.setActiveForShopping(walletDTO.isActiveForShopping());
        existingWallet.setActiveForWithdrawal(walletDTO.isActiveForWithdrawal());

        // Save the updated wallet
        Wallet updatedWallet = walletRepository.save(existingWallet);
        return convertToDTO(updatedWallet);
    }

    // Delete a wallet by ID
    public void deleteWallet(Long id) {
        if (!walletRepository.existsById(id)) {
            throw new WalletNotFoundException("Wallet not found with id: " + id);
        }
        walletRepository.deleteById(id);
    }

    // Update wallet balance (used by TransactionService)
    public void updateWalletBalance(Long walletId, Double amount, boolean isDeposit) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found with id: " + walletId));

        if (isDeposit) {
            wallet.setBalance(wallet.getBalance() + amount);
            wallet.setUsableBalance(wallet.getUsableBalance() + amount);
        } else {
            wallet.setBalance(wallet.getBalance() - amount);
            wallet.setUsableBalance(wallet.getUsableBalance() - amount);
        }

        walletRepository.save(wallet);
    }

    // Helper method to convert Wallet entity to WalletDTO
    private WalletDTO convertToDTO(Wallet wallet) {
        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setId(wallet.getId());
        walletDTO.setWalletName(wallet.getWalletName());
        walletDTO.setOwnerName(wallet.getOwnerName());
       // walletDTO.setCurrency(wallet.getCurrency());
        walletDTO.setBalance(wallet.getBalance());
        walletDTO.setUsableBalance(wallet.getUsableBalance());
        walletDTO.setActiveForShopping(wallet.isActiveForShopping());
        walletDTO.setActiveForWithdrawal(wallet.isActiveForWithdrawal());
        walletDTO.setUserId(wallet.getUser().getId());
        return walletDTO;
    }

    // Helper method to convert WalletDTO to Wallet entity
    private Wallet convertToEntity(WalletDTO walletDTO) {
        Wallet wallet = new Wallet();
        wallet.setId(walletDTO.getId());
        wallet.setWalletName(walletDTO.getWalletName());
        wallet.setOwnerName(walletDTO.getOwnerName());
      //  wallet.setCurrency(walletDTO.getCurrency()); // Adjust this if currency is a custom object
        wallet.setBalance(walletDTO.getBalance());
        wallet.setUsableBalance(walletDTO.getUsableBalance());
        wallet.setActiveForShopping(walletDTO.isActiveForShopping());
        wallet.setActiveForWithdrawal(walletDTO.isActiveForWithdrawal());

        // Set user
        Long userId = walletDTO.getUserId();
        if (userId == null) {
            throw new IllegalArgumentException("User ID is required for creating a wallet.");
        }

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " does not exist."));
        wallet.setUser(user);

        return wallet;
    }
}