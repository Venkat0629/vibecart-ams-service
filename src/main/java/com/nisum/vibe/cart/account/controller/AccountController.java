package com.nisum.vibe.cart.account.controller;

import com.nisum.vibe.cart.account.common.Status;
import com.nisum.vibe.cart.account.dto.*;
import com.nisum.vibe.cart.account.service.AccountService;
import com.nisum.vibe.cart.account.service.PasswordResetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vibe-cart/accounts")
//@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class AccountController {

    private AccountService accountService;
    private PasswordResetService passwordResetService;


    public AccountController(AccountService accountService, PasswordResetService passwordResetService) {
        this.accountService = accountService;
        this.passwordResetService = passwordResetService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(accountService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        UserDTO userDTO = accountService.createUser(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = accountService.getUserById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(accountService.updateUser(id, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(accountService.deleteUser(userId), HttpStatus.OK);
    }


    @PatchMapping("/user/{id}")
    public ResponseEntity<UserDTO> updateUserStatus(@PathVariable Long id, @RequestParam Status status) {
        return new ResponseEntity<>(accountService.updateUserStatus(id, status), HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return new ResponseEntity<>(accountService.getAllCustomers(), HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(accountService.createCustomer(customerDTO), HttpStatus.OK);
    }


    @PutMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
        return new ResponseEntity<>(accountService.updateCustomer(id, customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Long id) {
        return new ResponseEntity<>(accountService.deleteCustomer(id), HttpStatus.OK);

    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.getCustomerById(id), HttpStatus.OK);

    }

    @PatchMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> updateCustomerStatus(@PathVariable Long id, @RequestParam Status status) {
        return new ResponseEntity<>(accountService.updateCustomerStatus(id, status), HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateDTO> validateUser(@RequestParam String type, @RequestBody AuthRequest authRequest) {
        return new ResponseEntity<>(accountService.validate(type, authRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/forgot")
    public ResponseEntity<String> requestPasswordReset(@RequestParam String type, @RequestParam String email) {
        passwordResetService.requestPasswordReset(type, email);
        return new ResponseEntity<>("Password reset email sent " + email, HttpStatus.OK);
    }

    @PatchMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String type, @RequestBody PasswordReset passwordReset) {
        passwordResetService.resetPassword(type, passwordReset.getToken(), passwordReset.getPassword());
        return new ResponseEntity<>("Password has been reset", HttpStatus.OK);
    }

}
