package com.nisum.vibe.cart.account.dao;


import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="vibe_cart_account_password_reset_tokens")
public class PasswordResetTokenDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name="user_id")
    private UserDAO userDAO;
    @Column(name="token")
    private String token;
    @Column(name="expiryDate")
    private Instant expiryDate;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }
}
