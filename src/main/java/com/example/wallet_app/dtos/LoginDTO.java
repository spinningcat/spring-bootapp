package com.example.wallet_app.dtos;

public class LoginDTO {

    private String username;
    private String password;

    // Default constructor (required for JSON deserialization)
    public LoginDTO() {
    }

    // Parameterized constructor
    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}