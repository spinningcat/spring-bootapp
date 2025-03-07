package com.example.wallet_app.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(nullable = false, unique = true)
    private String sessionToken;

    @Column(nullable = false)
    private LocalDateTime loginTime;

    @Column(nullable = false)
    private boolean isActive;

    // Default constructor (required by JPA)
    public Login() {
    }

    // Parameterized constructor
    public Login(Users user, String sessionToken, LocalDateTime loginTime, boolean isActive) {
        this.user = user;
        this.sessionToken = sessionToken;
        this.loginTime = loginTime;
        this.isActive = isActive;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // toString method for debugging and logging
    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", user=" + user +
                ", sessionToken='" + sessionToken + '\'' +
                ", loginTime=" + loginTime +
                ", isActive=" + isActive +
                '}';
    }
}