package com.example.wallet_app.services;

import com.example.wallet_app.models.Login;
import com.example.wallet_app.models.Users;
import com.example.wallet_app.repositories.LoginRepository;
import com.example.wallet_app.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRepository loginRepository;

    public String login(String username, String password) {
        // Find the user by username
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Use BCrypt to match the raw password with the hashed password
        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Generate a session token
        String sessionToken = UUID.randomUUID().toString();

        // Save the login session
        Login login = new Login();
        login.setUser(user);
        login.setSessionToken(sessionToken);
        login.setLoginTime(LocalDateTime.now());
        login.setActive(true);
        loginRepository.save(login);

        return sessionToken;
    }

    public void logout(String sessionToken) {
        // Find the login session by session token
        Login login = loginRepository.findBySessionToken(sessionToken.split(" ")[1])
                .orElseThrow(() -> new RuntimeException("Session not found"));

            login.setActive(false);
            loginRepository.save(login);

    }
}