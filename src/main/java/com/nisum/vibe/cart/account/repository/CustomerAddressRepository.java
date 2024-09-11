package com.nisum.vibe.cart.account.repository;

import com.nisum.vibe.cart.account.dao.CustomerAddressDAO;
import com.nisum.vibe.cart.account.dto.CustomerAddressDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddressDAO,Long> {
    List<CustomerAddressDAO> findByCustomerDAOCustomerId(Long customerId);
}
