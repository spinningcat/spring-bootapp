package com.example.wallet_app.utils;

import com.example.wallet_app.models.Login;
import com.example.wallet_app.models.Users;
import com.example.wallet_app.repositories.LoginRepository;
import com.example.wallet_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CheckRole {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Check if the session is active and return the user's role.
     *
     * @param sessionToken The session token from the "Authorization" header.
     * @return The user's role if the session is active, otherwise null.
     */
    public String getRoleIfSessionActive(String sessionToken) {

        if (sessionToken == null || sessionToken.isEmpty()) {
            return null; // No session token provided
        }

        // Return the user's role*/
        return  loginRepository.findBySessionToken(sessionToken.split(" ")[1]).get().getUser().getRole();
    }
}
