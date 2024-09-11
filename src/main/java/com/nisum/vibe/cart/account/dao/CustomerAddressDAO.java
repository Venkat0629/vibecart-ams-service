package com.nisum.vibe.cart.account.dao;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "vibe_cart_account_cust_address")
public class CustomerAddressDAO {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long addressId;

        @ManyToOne
        @JoinColumn(name = "customer_id", nullable = false)
        private CustomerDAO customerDAO;

        @Column(name = "door_no", nullable = false, length = 20)
        private String doorNo;

        @Column(name = "street_name", nullable = false, length = 100)
        private String streetName;

        @Column(name = "land_mark", length = 100)
        private String landMark;

        @Column(name = "city_name", nullable = false, length = 50)private String cityName;

        @Column(name = "state_name", nullable = false, length = 50)private String stateName;

        @Column(name = "zip_code", nullable = false, length = 10)private String zipCode;

        @Column(name = "email", length = 100)private String email;

        @Column(name = "mobile", length = 20)private String mobile;

        @Column(name = "create_date")
        private Instant createDate;

        @Column(name = "update_date")
        private Instant updateDate;

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

        public long getAddressId() {
                return addressId;
        }

        public void setAddressId(long addressId) {
                this.addressId = addressId;
        }

        public CustomerDAO getCustomerDAO() {
                return customerDAO;
        }

        public void setCustomerDAO(CustomerDAO customerDAO) {
                this.customerDAO = customerDAO;
        }

        public String getDoorNo() {
                return doorNo;
        }

        public void setDoorNo(String doorNo) {
                this.doorNo = doorNo;
        }

        public String getStreetName() {
                return streetName;
        }

        public void setStreetName(String streetName) {
                this.streetName = streetName;
        }

        public String getLandMark() {
                return landMark;
        }

        public void setLandMark(String landMark) {
                this.landMark = landMark;
        }

        public String getCityName() {
                return cityName;
        }

        public void setCityName(String cityName) {
                this.cityName = cityName;
        }

        public String getStateName() {
                return stateName;
        }

        public void setStateName(String stateName) {
                this.stateName = stateName;
        }

        public String getZipCode() {
                return zipCode;
        }

        public void setZipCode(String zipCode) {
                this.zipCode = zipCode;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getMobile() {
                return mobile;
        }

        public void setMobile(String mobile) {
                this.mobile = mobile;
        }




}

