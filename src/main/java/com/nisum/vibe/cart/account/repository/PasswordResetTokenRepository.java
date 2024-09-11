package com.nisum.vibe.cart.account.repository;


import com.nisum.vibe.cart.account.dao.PasswordResetTokenDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenDAO, Long> {
    Optional<PasswordResetTokenDAO> findByToken(String token);
    void deleteByUserDAOUserId(Long userId);
}

