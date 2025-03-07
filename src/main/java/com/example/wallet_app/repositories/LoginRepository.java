package com.example.wallet_app.repositories;

import com.example.wallet_app.models.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findBySessionToken(String sessionToken);
}