package com.nisum.vibe.cart.account.repository;

import com.nisum.vibe.cart.account.dao.UserDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Long> {
    Optional<UserDAO> findByEmail(String email);
}
