package com.nisum.vibe.cart.account.dto;

import java.time.Instant;


public class AccountAuthDetailsDTO  {

    private long accountAuthId;
    private long userId;
    private String userName;
    private String emailId;
    private String password;
    private String lastPasswordOne;
    private Instant lastOneChangeDate;
    private String lastPasswordTwo;
    private Instant lastTwoChangeDate;
    private Instant createDate;
    private Instant updateDate;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public long getAccountAuthId() {
        return accountAuthId;
    }

    public void setAccountAuthId(long accountAuthId) {
        this.accountAuthId = accountAuthId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
