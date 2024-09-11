package com.nisum.vibe.cart.account.repository;

import com.nisum.vibe.cart.account.dao.AccountAuthDetailsDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountAuthDetailsRepository extends JpaRepository<AccountAuthDetailsDAO,Long> {}
