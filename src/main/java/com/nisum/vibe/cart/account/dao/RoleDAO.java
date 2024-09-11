package com.nisum.vibe.cart.account.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "vibe_cart_account_roles")
public class RoleDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role")
    private String role;

    @Column(name = "role_created_at")
    private Instant roleCreatedAt;

    @Column(name = "role_updated_at")
    private Instant roleUpdatedAt;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getRoleCreatedAt() {
        return roleCreatedAt;
    }

    public void setRoleCreatedAt(Instant roleCreatedAt) {
        this.roleCreatedAt = roleCreatedAt;
    }

    public Instant getRoleUpdatedAt() {
        return roleUpdatedAt;
    }

    public void setRoleUpdatedAt(Instant roleUpdatedAt) {
        this.roleUpdatedAt = roleUpdatedAt;
    }
}

