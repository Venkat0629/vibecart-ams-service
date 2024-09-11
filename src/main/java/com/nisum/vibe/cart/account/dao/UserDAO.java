package com.nisum.vibe.cart.account.dao;

import com.nisum.vibe.cart.account.common.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "vibe_cart_account_users")
public class UserDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "created_at")
    private Instant userDetailsCreatedAt;

    @Column(name = "updated_at")
    private Instant userDetailsUpdatedAt;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleDAO roleDAO;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public UserDAO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserDAO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public Instant getUserDetailsCreatedAt() {
        return userDetailsCreatedAt;
    }

    public void setUserDetailsCreatedAt(Instant userDetailsCreatedAt) {
        this.userDetailsCreatedAt = userDetailsCreatedAt;
    }

    public Instant getUserDetailsUpdatedAt() {
        return userDetailsUpdatedAt;
    }

    public void setUserDetailsUpdatedAt(Instant userDetailsUpdatedAt) {
        this.userDetailsUpdatedAt = userDetailsUpdatedAt;
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

    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
