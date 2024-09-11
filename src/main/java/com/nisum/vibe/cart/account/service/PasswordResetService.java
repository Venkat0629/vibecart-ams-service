package com.nisum.vibe.cart.account.service;

import com.nisum.vibe.cart.account.dao.CustomerDAO;
import com.nisum.vibe.cart.account.dao.PasswordResetTokenDAO;
import com.nisum.vibe.cart.account.dao.UserDAO;
import com.nisum.vibe.cart.account.exception.InvalidInputException;
import com.nisum.vibe.cart.account.repository.CustomerRepository;
import com.nisum.vibe.cart.account.repository.PasswordResetTokenRepository;
import com.nisum.vibe.cart.account.repository.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final PasswordResetTokenRepository tokenRepository;

    public PasswordResetService(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender mailSender, PasswordResetTokenRepository tokenRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
        this.tokenRepository = tokenRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void requestPasswordReset(String type, String email) {
        if (type.equals("user")) {
            UserDAO userDAO = userRepository.findByEmail(email).orElseThrow(() -> new InvalidInputException("User not found with email: " + email));
            String token = UUID.randomUUID().toString().substring(0, 5).replaceAll("-", "A");
            saveResetToken(userDAO.getEmail(), token);
            sendPasswordResetEmail(email, token);
        } else if (type.equals("customer")) {
            CustomerDAO customerDAO = customerRepository.findByEmail(email).orElseThrow(() -> new InvalidInputException("Customer not found with email: " + email));
            String resetToken = UUID.randomUUID().toString().substring(0, 5).replaceAll("-", "A");
            saveResetToken(customerDAO.getEmail(), resetToken);
            sendPasswordResetEmail(email, resetToken);
        }
    }

    @Transactional
    public void resetPassword(String type, String token, String newPassword) {
        String email = verifyResetToken(token);
        String encodedPassword = passwordEncoder.encode(newPassword);
        if (type.equals("user")) {
            UserDAO userDAO = userRepository.findByEmail(email).orElseThrow(() -> new InvalidInputException("User not found with ID: " + email));
            userDAO.setPassword(encodedPassword);
            userRepository.save(userDAO);
            deleteResetToken(userDAO.getUserId());
        } else if (type.equals("customer")) {
            CustomerDAO customerDAO = customerRepository.findByEmail(email).orElseThrow(() -> new InvalidInputException("Customer not found with ID: " + email));
            customerDAO.setPassword(encodedPassword);
            customerRepository.save(customerDAO);
            deleteResetToken(customerDAO.getCustomerId());
        }
    }

    private void sendPasswordResetEmail(String email, String resetToken) {
        String resetUrl = "http://localhost:3000/reset-password?token=" + resetToken;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, please click the link below:\n" + resetUrl);
        mailSender.send(message);
    }

    private void saveResetToken(String email, String resetToken) {
        PasswordResetTokenDAO tokenDAO = new PasswordResetTokenDAO();
        Optional<UserDAO> userDAO = userRepository.findByEmail(email);
        tokenDAO.setUserDAO(userDAO.get());
        tokenDAO.setToken(resetToken);
        tokenDAO.setExpiryDate(Instant.now().plusSeconds(3600));
        tokenRepository.save(tokenDAO);
    }

    private String verifyResetToken(String resetToken) {
        PasswordResetTokenDAO tokenDAO = tokenRepository.findByToken(resetToken).orElseThrow(() -> new InvalidInputException("Invalid or expired reset token"));
        if (tokenDAO.getExpiryDate().isBefore(Instant.now())) {
            throw new InvalidInputException("Reset token has expired");
        }
        return tokenDAO.getUserDAO().getEmail();
    }

    private void deleteResetToken(Long userId) {
        tokenRepository.deleteByUserDAOUserId(userId);
    }
}
