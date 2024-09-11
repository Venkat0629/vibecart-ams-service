package com.nisum.vibe.cart.account.constants;

public class AccountManagementConstants {
    public static String INVALID_INPUT_EXCEPTION_MESSAGE_FORMAT = "%s should not be null or empty";
    public static String INVALID_EMAIL_ID_EXCEPTION_MESSAGE_FORMAT = "Please Provide Valid Email ";
    public static String INVALID_PASSWORD_EXCEPTION_MESSAGE_FORMAT = "Please Provide Valid Password";
    public static String INVALID_ROLE_EXCEPTION_MESSAGE_FORMAT = "Please Provide valid Role Name";
    public static String INVALID_USER_ID_EXCEPTION_MESSAGE_FORMAT = "User with ID %d not found";
    public static String DELETE_MESSAGE_FORMAT="Record deleted Successfully with that %d";
    public static String CUSTOMERDTO_EXCEPTION_MESSAGE_FORMAT="Customer DTO should not be null";
    public static String VALID_MESSAGE_FORMAT = "User is Valid";
    public static String USERDTO_EXCEPTION_MESSAGE_FORMAT="User DTO should not be null";
    public static String FIRST_NAME="First Name";
    public static String LAST_NAME="Last Name";
    public static String ROLE="Role";
    public static String EMAIL_ID="Email Id";
    public static String PASSWORD="Password";
    public static String MOBILE_NUMBER="Mobile Number";
    public static String ADDRESSES="Customer Addresses";

    public static String getInvalidInputExceptionMessageFormat(String name) {
        return String.format( INVALID_INPUT_EXCEPTION_MESSAGE_FORMAT,name);
    }

    public static String getInvalidUserIdExceptionMessageFormat(Long userId) {
        return String.format(INVALID_USER_ID_EXCEPTION_MESSAGE_FORMAT, userId);
    }
    public static String getDeleteMessageFormat(Long id)
    {
        return String.format(DELETE_MESSAGE_FORMAT,id);
    }

}
