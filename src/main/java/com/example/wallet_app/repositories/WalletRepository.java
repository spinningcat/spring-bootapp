package com.example.wallet_app.repositories;

import com.example.wallet_app.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    /**
     * Find all wallets associated with a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of wallets belonging to the user.
     */
    List<Wallet> findByUserId(Long userId);

    /**
     * Find all wallets with a specific currency.
     *
     * @param currency The currency to filter by.
     * @return A list of wallets with the specified currency.
     */
    List<Wallet> findByCurrency(String currency);

    /**
     * Find all wallets that are active for shopping.
     *
     * @param activeForShopping Whether the wallet is active for shopping.
     * @return A list of wallets that are active for shopping.
     */
    List<Wallet> findByActiveForShopping(boolean activeForShopping);

    /**
     * Find all wallets that are active for withdrawal.
     *
     * @param activeForWithdrawal Whether the wallet is active for withdrawal.
     * @return A list of wallets that are active for withdrawal.
     */
    List<Wallet> findByActiveForWithdrawal(boolean activeForWithdrawal);

    /**
     * Find all wallets with a balance greater than or equal to the specified amount.
     *
     * @param amount The minimum balance amount.
     * @return A list of wallets with a balance greater than or equal to the specified amount.
     */
    List<Wallet> findByBalanceGreaterThanEqual(Double amount);

    /**
     * Find all wallets with a usable balance greater than or equal to the specified amount.
     *
     * @param amount The minimum usable balance amount.
     * @return A list of wallets with a usable balance greater than or equal to the specified amount.
     */
    List<Wallet> findByUsableBalanceGreaterThanEqual(Double amount);
}