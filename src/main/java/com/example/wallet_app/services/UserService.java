package com.example.wallet_app.services;

import com.example.wallet_app.models.Users;
import com.example.wallet_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by ID
    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }



    // Create a new user
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    // Update an existing user
    public Users updateUser(Long id, Users userDetails) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setName(userDetails.getName());
        user.setSurname(userDetails.getSurname());
        user.setTCKN(userDetails.getTCKN());
        user.setRole(userDetails.getRole());

        return userRepository.save(user);
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }
}