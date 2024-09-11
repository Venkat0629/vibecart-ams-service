package com.nisum.vibe.cart.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class AccountManagementAdvice {
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<MessageError>handleInvalidInputException(InvalidInputException invalidInputException)
    {
        MessageError messageError=new MessageError();
        System.out.println(invalidInputException.getMessage());
        messageError.setErrorMessage(invalidInputException.getMessage());
        messageError.setTime(Instant.now());
        return new ResponseEntity<>(messageError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InValidRoleNameException.class)
    public ResponseEntity<MessageError>handleInvalidRoleExeptoption(InValidRoleNameException inValidRoleNameException)
    {
        MessageError messageError=new MessageError();
        messageError.setErrorMessage(inValidRoleNameException.getMessage());
        messageError.setTime(Instant.now());
        return new ResponseEntity<>(messageError,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InValidEmailException.class)
    public ResponseEntity<MessageError>handleInvalidEmailException(InValidEmailException inValidEmailException)
    {
        MessageError messageError=new MessageError();
        messageError.setErrorMessage(inValidEmailException.getMessage());
        messageError.setTime(Instant.now());
        return new ResponseEntity<>(messageError,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<MessageError>handleInvalidPasswordException(InvalidPasswordException invalidPasswordException)
    {
        MessageError messageError=new MessageError();
        messageError.setErrorMessage(invalidPasswordException.getMessage());
        messageError.setTime(Instant.now());
        return new ResponseEntity<>(messageError,HttpStatus.BAD_REQUEST);
    }


}
