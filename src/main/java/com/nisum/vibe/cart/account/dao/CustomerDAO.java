package com.nisum.vibe.cart.account.dao;

import com.nisum.vibe.cart.account.common.Status;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "vibe_cart_account_customer")
public class CustomerDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleDAO roleDAO;

    @OneToMany(mappedBy = "customerDAO", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerAddressDAO> customerAddressDAOList;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "create_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createDate;

    @Column(name = "update_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Instant updateDate;

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

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

    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
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


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public CustomerDAO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public List<CustomerAddressDAO> getCustomerAddressDAOList() {
        return customerAddressDAOList;
    }

    public void setCustomerAddressDAOList(List<CustomerAddressDAO> customerAddressDAOList) {
        this.customerAddressDAOList = customerAddressDAOList;
    }
}
