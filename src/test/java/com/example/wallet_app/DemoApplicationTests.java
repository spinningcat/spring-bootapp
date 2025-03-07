package com.example.wallet_app;

import com.example.wallet_app.dtos.WalletDTO;
import com.example.wallet_app.enums.WalletCurrency;
import com.example.wallet_app.enums.TransactionStatus;
import com.example.wallet_app.enums.TransactionType;
import com.example.wallet_app.models.Transaction;
import com.example.wallet_app.models.Wallet;
import com.example.wallet_app.repositories.TransactionRepository;
import com.example.wallet_app.repositories.WalletRepository;
import com.example.wallet_app.services.TransactionService;
import com.example.wallet_app.services.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DemoApplicationTests {

	@Mock
	private WalletRepository walletRepository;

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private WalletService walletService;

	@InjectMocks
	private TransactionService transactionService;

	@Test
	void testCreateWallet() {
		// Arrange
		WalletDTO walletDTO = new WalletDTO();
		walletDTO.setOwnerName("John Doe");
		walletDTO.setCurrency(WalletCurrency.USD);
		walletDTO.setBalance(100.0);

		Wallet wallet = new Wallet();
		wallet.setId(1L);
		wallet.setOwnerName("John Doe");
		wallet.setCurrency("USD");
		wallet.setBalance(100.0);

		when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);

		// Act
		WalletDTO result = walletService.createWallet(walletDTO);

		// Assert
		assertNotNull(result);
		assertEquals("John Doe", result.getOwnerName());
		assertEquals("USD", result.getCurrency());
		assertEquals(100.0, result.getBalance());
	}

	@Test
	void testDepositFunds() {
		// Arrange
		Long walletId = 1L;
		Double amount = 200.0;

		Wallet wallet = new Wallet();
		wallet.setId(walletId);
		wallet.setBalance(100.0);

		when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));
		when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);

		// Act
		walletService.updateWalletBalance(walletId, amount, true);

		// Assert
		assertEquals(300.0, wallet.getBalance());
		verify(walletRepository, times(1)).save(wallet);
	}


}