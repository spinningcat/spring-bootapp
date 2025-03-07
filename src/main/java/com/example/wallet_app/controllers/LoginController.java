package com.example.wallet_app.controllers;

import com.example.wallet_app.dtos.LoginDTO; // Import the LoginRequest class
import com.example.wallet_app.services.LoginService;
import com.example.wallet_app.utils.SessionActive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;
    private SessionActive sessionActive;

    public LoginController(LoginService loginService,SessionActive sessionActive) {
        this.loginService = loginService;
        this.sessionActive = sessionActive;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO request) {
        return loginService.login(request.getUsername(), request.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String sessionToken) {
        if (sessionToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
        }

        // Assuming you have a method in your service to check if the session is active
        if (!sessionActive.isSessionActive(sessionToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 401 Unauthorized
        }

        loginService.logout(sessionToken);
        return ResponseEntity.ok().build(); // 200 OK
    }

}