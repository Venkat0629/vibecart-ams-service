package com.nisum.vibe.cart.account.dto;

import com.nisum.vibe.cart.account.common.Status;

import java.util.List;

public class ValidateDTO {

    private Long id;
    private String name;
    private String email;
    private Status status;
    private String role;

    public Long getId() {
        return id;
    }

    public ValidateDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ValidateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ValidateDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public ValidateDTO setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getRole() {
        return role;
    }

    public ValidateDTO setRole(String role) {
        this.role = role;
        return this;
    }
}
