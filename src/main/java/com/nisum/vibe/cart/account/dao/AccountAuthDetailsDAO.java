package com.nisum.vibe.cart.account.dao;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "vibe_cart_account_acc_auth")
public class AccountAuthDetailsDAO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountAuthId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserDAO user;
    @Column(name = "last_password_one", length = 255)
    private String lastPasswordOne;

    @Column(name = "last_one_change_date")
    private Instant lastOneChangeDate;

    @Column(name = "last_password_two", length = 255)
    private String lastPasswordTwo;

    @Column(name = "last_two_change_date")
    private Instant lastTwoChangeDate;

    @Column(name = "create_date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createDate;

    @Column(name = "update_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Instant updateDate;

    public long getAccountAuthId() {
        return accountAuthId;
    }

    public void setAccountAuthId(long accountAuthId) {
        this.accountAuthId = accountAuthId;
    }

    public UserDAO getUser() {
        return user;
    }

    public void setUser(UserDAO user) {
        this.user = user;
    }



    public String getLastPasswordOne() {
        return lastPasswordOne;
    }

    public void setLastPasswordOne(String lastPasswordOne) {
        this.lastPasswordOne = lastPasswordOne;
    }




    public String getLastPasswordTwo() {
        return lastPasswordTwo;
    }

    public void setLastPasswordTwo(String lastPasswordTwo) {
        this.lastPasswordTwo = lastPasswordTwo;
    }

    public Instant getLastOneChangeDate() {
        return lastOneChangeDate;
    }

    public void setLastOneChangeDate(Instant lastOneChangeDate) {
        this.lastOneChangeDate = lastOneChangeDate;
    }

    public Instant getLastTwoChangeDate() {
        return lastTwoChangeDate;
    }

    public void setLastTwoChangeDate(Instant lastTwoChangeDate) {
        this.lastTwoChangeDate = lastTwoChangeDate;
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
}
