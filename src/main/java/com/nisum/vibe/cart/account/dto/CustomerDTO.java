package com.nisum.vibe.cart.account.dto;

import com.nisum.vibe.cart.account.common.Status;

import java.time.Instant;
import java.util.List;

public class CustomerDTO {

    private long customerId;
    private String firstName;
    private String lastName;
    private String role;
    private String password;
    private Status status;
    private String email;
    private List<CustomerAddressDTO> customerAddressDTOList;

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<CustomerAddressDTO> getCustomerAddressDTOList() {
        return customerAddressDTOList;
    }

    public void setCustomerAddressDTOList(List<CustomerAddressDTO> customerAddressDTOList) {
        this.customerAddressDTOList = customerAddressDTOList;
    }

    public String getEmail() {
        return email;
    }

    public CustomerDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
