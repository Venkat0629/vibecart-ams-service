package com.nisum.vibe.cart.account.repository;

import com.nisum.vibe.cart.account.dao.RoleDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<RoleDAO, Long> {
    Optional<RoleDAO> findByRole(String role);
}
