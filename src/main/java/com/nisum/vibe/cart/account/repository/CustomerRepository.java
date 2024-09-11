package com.nisum.vibe.cart.account.repository;

import com.nisum.vibe.cart.account.dao.CustomerDAO;

import com.nisum.vibe.cart.account.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDAO, Long> {
    Optional<CustomerDAO> findByEmail(String email);
}
