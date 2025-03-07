package com.example.wallet_app.services;

import com.example.wallet_app.models.Users;
import com.example.wallet_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt; // Import BCrypt

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    public Users registerUser(String username, String password, String role, String name, String surname, String tckn) {
        // Hash the password using BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Create the user with the hashed password
        Users user = new Users(username, hashedPassword, role, name, surname, tckn);

        // Save the user to the database
        return userRepository.save(user);
    }
}