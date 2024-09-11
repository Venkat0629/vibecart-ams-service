
package com.nisum.vibe.cart.account.validation;

import com.nisum.vibe.cart.account.constants.AccountManagementConstants;
import com.nisum.vibe.cart.account.dto.CustomerDTO;
import com.nisum.vibe.cart.account.dto.UserDTO;
import com.nisum.vibe.cart.account.exception.InvalidInputException;

import java.util.Objects;

public class Validate {
    public static void ValidateUser(UserDTO userDTO) {
        if (userDTO == null) {
            throw new InvalidInputException(AccountManagementConstants.USERDTO_EXCEPTION_MESSAGE_FORMAT);
        }
        if (userDTO.getRole() == null || Objects.equals(userDTO.getRole(), ""))
            throw new InvalidInputException(AccountManagementConstants.getInvalidInputExceptionMessageFormat(AccountManagementConstants.ROLE));
        if (userDTO.getEmail() == null || Objects.equals(userDTO.getEmail(), ""))
            throw new InvalidInputException(AccountManagementConstants.getInvalidInputExceptionMessageFormat(AccountManagementConstants.EMAIL_ID));
        if (userDTO.getFirstName() == null || Objects.equals(userDTO.getFirstName(), ""))
            throw new InvalidInputException(AccountManagementConstants.getInvalidInputExceptionMessageFormat(AccountManagementConstants.FIRST_NAME));
        if (userDTO.getLastName() == null || Objects.equals(userDTO.getLastName(), ""))
            throw new InvalidInputException(AccountManagementConstants.getInvalidInputExceptionMessageFormat(AccountManagementConstants.LAST_NAME));
        if (userDTO.getPassword() == null || Objects.equals(userDTO.getPassword(), ""))
            throw new InvalidInputException(AccountManagementConstants.getInvalidInputExceptionMessageFormat(AccountManagementConstants.PASSWORD));
        if (userDTO.getMobile() == null || Objects.equals(userDTO.getMobile(), ""))
            throw new InvalidInputException(AccountManagementConstants.getInvalidInputExceptionMessageFormat(AccountManagementConstants.MOBILE_NUMBER));
    }


        public static void validateCustomer(CustomerDTO customerDTO) {
            if (customerDTO == null) {
                throw new InvalidInputException(AccountManagementConstants.CUSTOMERDTO_EXCEPTION_MESSAGE_FORMAT);
            }
            if (customerDTO.getFirstName() == null || customerDTO.getFirstName().isEmpty()) {
                throw new InvalidInputException(AccountManagementConstants.getInvalidInputExceptionMessageFormat(AccountManagementConstants.FIRST_NAME));
            }
            if (customerDTO.getLastName() == null || customerDTO.getLastName().isEmpty()) {
                throw new InvalidInputException(AccountManagementConstants.getInvalidInputExceptionMessageFormat(AccountManagementConstants.LAST_NAME));
            }
            if (customerDTO.getRole() == null) {
                throw new InvalidInputException(AccountManagementConstants.getInvalidInputExceptionMessageFormat(AccountManagementConstants.ROLE));
            }
            if (customerDTO.getPassword() == null || customerDTO.getPassword().isEmpty()) {
                throw new InvalidInputException(AccountManagementConstants.getInvalidInputExceptionMessageFormat(AccountManagementConstants.PASSWORD));
            }
            if (customerDTO.getCustomerAddressDTOList() == null || customerDTO.getCustomerAddressDTOList().isEmpty()) {
                throw new InvalidInputException(AccountManagementConstants.getInvalidInputExceptionMessageFormat(AccountManagementConstants.ADDRESSES));
            }

    }


}