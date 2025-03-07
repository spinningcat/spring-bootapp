package com.example.wallet_app.utils;

import com.example.wallet_app.models.Login;
import com.example.wallet_app.repositories.LoginRepository;
import com.example.wallet_app.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionActive {
    private final LoginRepository loginRepository;

    @Autowired
    public SessionActive(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public Boolean isSessionActive(String sessionToken) {
        // Split the sessionToken if needed (e.g., "Bearer <token>")
        String token = sessionToken.split(" ")[1];
        System.out.println(token);
        // Find the Login entity by sessionToken
        Login login = loginRepository.findBySessionToken(token)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        // Return true or false based on the isActive status
        return login.isActive() ? true : false;
    }
}
