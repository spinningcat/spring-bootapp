package com.example.wallet_app.controllers;

import com.example.wallet_app.dtos.WalletDTO;
import com.example.wallet_app.exceptions.WalletNotFoundException;
import com.example.wallet_app.models.Users;
import com.example.wallet_app.repositories.LoginRepository;
import com.example.wallet_app.services.WalletService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling HTTP requests related to Wallet entities.
 */
@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;
    private LoginRepository loginRepository;

    @Autowired
    public WalletController(WalletService walletService, LoginRepository loginRepository) {
        this.walletService = walletService;
        this.loginRepository = loginRepository;
    }

    /**
     * Handles GET requests to retrieve all wallets.
     * @return A list of all wallets.
     */
    @GetMapping
    public ResponseEntity<List<WalletDTO>> getAllWallets(@RequestHeader("Authorization") String sessionToken) {

        return new ResponseEntity<>(walletService.getAllWallets(), HttpStatus.OK);
    }

    /**
     * Handles POST requests to create a new wallet.
     * @param walletDTO The wallet DTO to be created.
     * @return The newly created wallet DTO.
     */

    @PostMapping
    public ResponseEntity<WalletDTO> createWallet(@RequestBody WalletDTO walletDTO, @RequestHeader("Authorization") String sessionToken) {
        // Extract the session token (assuming it's in the format "Bearer <token>")
        String token = sessionToken.split(" ")[1];

        // Use the session token to find the user ID
        Long userId = loginRepository.findBySessionToken(token).get().getUser().getId();

        // Set the user ID in the WalletDTO
        walletDTO.setUserId(userId);

        return new ResponseEntity<>(walletService.createWallet(walletDTO), HttpStatus.CREATED);
    }

    /**
     * Handles GET requests to retrieve a wallet by ID.
     * @param id The ID of the wallet to retrieve.
     * @return The wallet DTO with the specified ID.
     */
    @GetMapping("{id}")
    public ResponseEntity<WalletDTO> getWalletById(@PathVariable Long id) {
        WalletDTO walletDTO = walletService.getWalletById(id);
        if (walletDTO != null) {
            return ResponseEntity.ok(walletDTO);
        } else {
            throw new WalletNotFoundException("Wallet not found with id: " + id);
        }
    }

    /**
     * Handles PUT requests to update an existing wallet.
     * @param id The ID of the wallet to update.
     * @param walletDTO The updated wallet DTO.
     * @return The updated wallet DTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<WalletDTO> updateWallet(@PathVariable Long id, @RequestBody WalletDTO walletDTO) {
        walletDTO.setId(id); // Ensure the ID is set correctly
        WalletDTO updatedWalletDTO = walletService.updateWallet(walletDTO);
        if (updatedWalletDTO != null) {
            return new ResponseEntity<>(updatedWalletDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handles DELETE requests to delete a wallet by ID.
     * @param id The ID of the wallet to delete.
     * @return A successful response without content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
        walletService.deleteWallet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
