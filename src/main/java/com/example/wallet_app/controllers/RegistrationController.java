package com.example.wallet_app.controllers;

import com.example.wallet_app.dtos.RegistrationDTO;
import com.example.wallet_app.models.Users;
import com.example.wallet_app.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public Users register(@RequestBody RegistrationDTO request) {
        return registrationService.registerUser(
                request.getUsername(),
                request.getPassword(),
                request.getRole(),
                request.getName(),
                request.getSurname(),
                request.getTckn()
        );
    }
}