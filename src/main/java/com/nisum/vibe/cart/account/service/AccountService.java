package com.nisum.vibe.cart.account.service;

import com.nisum.vibe.cart.account.common.Status;
import com.nisum.vibe.cart.account.constants.AccountManagementConstants;
import com.nisum.vibe.cart.account.dao.*;
import com.nisum.vibe.cart.account.dto.*;
import com.nisum.vibe.cart.account.exception.InValidEmailException;
import com.nisum.vibe.cart.account.exception.InValidRoleNameException;
import com.nisum.vibe.cart.account.exception.InvalidInputException;
import com.nisum.vibe.cart.account.exception.InvalidPasswordException;
import com.nisum.vibe.cart.account.repository.*;
import com.nisum.vibe.cart.account.validation.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository customerAddressRepository;
    private final AccountAuthDetailsRepository accountAuthDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(UserRepository userRepository, RoleRepository roleRepository, CustomerRepository customerRepository, CustomerAddressRepository customerAddressRepository, AccountAuthDetailsRepository accountAuthDetailsRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.customerRepository = customerRepository;
        this.customerAddressRepository = customerAddressRepository;
        this.accountAuthDetailsRepository = accountAuthDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setRole("ADMIN");
        Validate.ValidateUser(userDTO);
        UserDAO userDAO = new UserDAO();
        BeanUtils.copyProperties(userDTO, userDAO);
        userDAO.setStatus(Status.ACTIVE);
        String encodedPassword1 = passwordEncoder.encode(userDTO.getPassword());
        System.out.println(encodedPassword1);
        userDAO.setPassword(encodedPassword1);
        if (userDTO.getRole() != null) {
            RoleDAO roleDAO = roleRepository.findByRole(userDTO.getRole()).orElseThrow(() -> new RuntimeException("Role not found"));
            userDAO.setRoleDAO(roleDAO);
        } else {
            throw new InValidRoleNameException(AccountManagementConstants.INVALID_ROLE_EXCEPTION_MESSAGE_FORMAT);
        }

        userDAO.setUserDetailsCreatedAt(Instant.now());
        userDAO.setUserDetailsUpdatedAt(Instant.now());

        UserDAO savedUser = userRepository.save(userDAO);

        AccountAuthDetailsDTO accountAuthDetailsDTO = new AccountAuthDetailsDTO();
        accountAuthDetailsDTO.setPassword(encodedPassword1);
        accountAuthDetailsDTO.setEmailId(userDTO.getEmail());
        accountAuthDetailsDTO.setUserId(savedUser.getUserId());
        createAccountAuthDetails(accountAuthDetailsDTO);
        UserDTO savedUserDTO = new UserDTO();
        BeanUtils.copyProperties(savedUser, savedUserDTO);
        savedUserDTO.setRole(savedUser.getRoleDAO().getRole());
        savedUserDTO.setPassword("");
        return savedUserDTO;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userDAO -> {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userDAO, userDTO);

            if (userDAO.getRoleDAO() != null) {
                userDTO.setRole(userDAO.getRoleDAO().getRole());
            } else {
                throw new InValidRoleNameException(AccountManagementConstants.INVALID_ROLE_EXCEPTION_MESSAGE_FORMAT);
            }

            userDTO.setPassword(""); // Clear password in DTO
            return userDTO;
        }).collect(Collectors.toList());
    }

    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        userDTO.setRole("ADMIN");
        Validate.ValidateUser(userDTO);
        UserDAO existingUser = userRepository.findById(userId).orElseThrow(() -> new InvalidInputException(AccountManagementConstants.getInvalidUserIdExceptionMessageFormat(userId)));
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        BeanUtils.copyProperties(userDTO, existingUser, "userId", "userDetailsCreatedAt", "roleDAO");
        if (userDTO.getRole() != null) {
            RoleDAO roleDAO = roleRepository.findByRole(userDTO.getRole()).orElseThrow(() -> new InValidRoleNameException(AccountManagementConstants.INVALID_ROLE_EXCEPTION_MESSAGE_FORMAT));
            existingUser.setRoleDAO(roleDAO);
        }

        existingUser.setUserDetailsUpdatedAt(Instant.now());
        existingUser.setStatus(Status.ACTIVE);
        existingUser.setPassword(encodedPassword);

        UserDAO updatedUserDAO = userRepository.save(existingUser);

        UserDTO updatedUserDTO = new UserDTO();
        BeanUtils.copyProperties(updatedUserDAO, updatedUserDTO);
        updatedUserDTO.setRole(updatedUserDAO.getRoleDAO().getRole());
        updatedUserDTO.setPassword(""); // Clear password in DTO

        return updatedUserDTO;
    }

    @Transactional
    public String deleteUser(Long userId) {
        Optional<UserDAO> userDAO = userRepository.findById(userId);
        if (userDAO.isPresent()) {
            accountAuthDetailsRepository.deleteById(userId);
            userRepository.deleteById(userId);
            return AccountManagementConstants.getDeleteMessageFormat(userId);
        }
        return "some error is there";
    }


    public UserDTO getUserById(Long userId) {
        UserDAO userDAO = userRepository.findById(userId).orElseThrow(() -> new InvalidInputException(AccountManagementConstants.getInvalidUserIdExceptionMessageFormat(userId)));

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDAO, userDTO);
        userDTO.setRole(userDAO.getRoleDAO().getRole());
        userDTO.setPassword(""); // Clear password in DTO
        return userDTO;
    }

    public UserDTO updateUserStatus(Long userId, Status newStatus) {
        UserDAO userDAO = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        userDAO.setStatus(newStatus);
        userDAO.setUserDetailsUpdatedAt(Instant.now());

        UserDAO updatedUserDAO = userRepository.save(userDAO);

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(updatedUserDAO, userDTO);
        userDTO.setRole(updatedUserDAO.getRoleDAO().getRole());
        userDTO.setPassword(""); // Clear password in DTO
        userDTO.setRole("ADMIN");
        return userDTO;
    }

    public ValidateDTO validate(String type, AuthRequest authRequest) {
        UserDAO userDAO;
        CustomerDAO customerDAO;
        ValidateDTO validateDTO = new ValidateDTO();
        RoleDAO roleDAO = roleRepository.findByRole(authRequest.getRole()).orElse(null);
        if (type.equals("user")) {
            userDAO = userRepository.findByEmail(authRequest.getEmail()).orElse(null);
            if (userDAO == null) {
                throw new InValidEmailException(AccountManagementConstants.INVALID_EMAIL_ID_EXCEPTION_MESSAGE_FORMAT);
            }
            if (roleDAO == null || !Objects.equals(userDAO.getRoleDAO().getRole(), roleDAO.getRole())) {
                throw new InValidRoleNameException(AccountManagementConstants.INVALID_ROLE_EXCEPTION_MESSAGE_FORMAT);
            }
            if (passwordEncoder.matches(authRequest.getPassword(), userDAO.getPassword())) {
                validateDTO.setEmail(userDAO.getEmail());
                validateDTO.setId(userDAO.getUserId());
                validateDTO.setName(userDAO.getFirstName() + " " + userDAO.getLastName());
                validateDTO.setRole(userDAO.getRoleDAO().getRole());
                validateDTO.setStatus(userDAO.getStatus());
                return validateDTO;
            }
        } else if (type.equals("customer")) {
            customerDAO = customerRepository.findByEmail(authRequest.getEmail()).orElse(null);
            if (customerDAO == null) {
                throw new InValidEmailException(AccountManagementConstants.INVALID_EMAIL_ID_EXCEPTION_MESSAGE_FORMAT);
            }
            if (roleDAO == null || !Objects.equals(customerDAO.getRoleDAO().getRole(), roleDAO.getRole())) {
                throw new InValidRoleNameException(AccountManagementConstants.INVALID_ROLE_EXCEPTION_MESSAGE_FORMAT);
            }
            if (passwordEncoder.matches(authRequest.getPassword(), customerDAO.getPassword())) {
                validateDTO.setEmail(customerDAO.getEmail());
                validateDTO.setId(customerDAO.getCustomerId());
                validateDTO.setName(customerDAO.getFirstName() + " " + customerDAO.getLastName());
                validateDTO.setRole(customerDAO.getRoleDAO().getRole());
                validateDTO.setStatus(customerDAO.getStatus());
                return validateDTO;
            }
        }
        throw new InvalidPasswordException(AccountManagementConstants.INVALID_PASSWORD_EXCEPTION_MESSAGE_FORMAT);
    }

    public AccountAuthDetailsDTO createAccountAuthDetails(AccountAuthDetailsDTO accountAuthDetailsDTO) {
        AccountAuthDetailsDAO accountAuthDetailsDAO = new AccountAuthDetailsDAO();
        BeanUtils.copyProperties(accountAuthDetailsDTO, accountAuthDetailsDAO);

        UserDAO user = userRepository.findById(accountAuthDetailsDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        accountAuthDetailsDAO.setUser(user);

        accountAuthDetailsDAO.setCreateDate(Instant.now());
        accountAuthDetailsDAO.setUpdateDate(Instant.now());

        AccountAuthDetailsDAO savedAuthDetails = accountAuthDetailsRepository.save(accountAuthDetailsDAO);

        AccountAuthDetailsDTO savedAuthDetailsDTO = new AccountAuthDetailsDTO();
        BeanUtils.copyProperties(savedAuthDetails, savedAuthDetailsDTO);

        return savedAuthDetailsDTO;
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        customerDTO.setRole("GUEST");
        Validate.validateCustomer(customerDTO);
        CustomerDAO customerDAO = new CustomerDAO();
        BeanUtils.copyProperties(customerDTO, customerDAO);
        String encodedPassword = passwordEncoder.encode(customerDTO.getPassword());
        customerDAO.setPassword(encodedPassword);
        if (customerDTO.getRole() != null) {
            RoleDAO roleDAO = roleRepository.findByRole(customerDTO.getRole()).orElseThrow(() -> new RuntimeException("Role not found"));
            customerDAO.setRoleDAO(roleDAO);
        }
        customerDAO.setStatus(Status.ACTIVE);
        if (customerDTO.getCustomerAddressDTOList() != null) {
            List<CustomerAddressDAO> addressDAOs = customerDTO.getCustomerAddressDTOList().stream().map(addressDTO -> {
                CustomerAddressDAO addressDAO = new CustomerAddressDAO();
                BeanUtils.copyProperties(addressDTO, addressDAO);
                addressDAO.setCreateDate(Instant.now());
                addressDAO.setUpdateDate(Instant.now());
                addressDAO.setCustomerDAO(customerDAO);
                return addressDAO;
            }).collect(Collectors.toList());
            customerDAO.setCustomerAddressDAOList(addressDAOs);
        }

        customerDAO.setCreateDate(Instant.now());
        customerDAO.setUpdateDate(Instant.now());

        CustomerDAO savedCustomerDAO = customerRepository.save(customerDAO);

        CustomerDTO savedCustomerDTO = new CustomerDTO();
        BeanUtils.copyProperties(savedCustomerDAO, savedCustomerDTO);

        if (savedCustomerDAO.getCustomerAddressDAOList() != null) {
            List<CustomerAddressDTO> addressDTOList = savedCustomerDAO.getCustomerAddressDAOList().stream().map(addressDAO -> {
                CustomerAddressDTO addressDTO = new CustomerAddressDTO();
                BeanUtils.copyProperties(addressDAO, addressDTO);
                return addressDTO;
            }).collect(Collectors.toList());
            savedCustomerDTO.setCustomerAddressDTOList(addressDTOList);
        }
        savedCustomerDTO.setPassword("");
        savedCustomerDTO.setRole(savedCustomerDAO.getRoleDAO().getRole());
        return savedCustomerDTO;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerDAO -> {
            CustomerDTO customerDTO = new CustomerDTO();
            BeanUtils.copyProperties(customerDAO, customerDTO);

            if (customerDAO.getCustomerAddressDAOList() != null) {
                List<CustomerAddressDTO> addressDTOList = customerDAO.getCustomerAddressDAOList().stream().map(addressDAO -> {
                    CustomerAddressDTO addressDTO = new CustomerAddressDTO();
                    BeanUtils.copyProperties(addressDAO, addressDTO);
                    return addressDTO;
                }).collect(Collectors.toList());
                customerDTO.setCustomerAddressDTOList(addressDTOList);
            }

            customerDTO.setRole(customerDAO.getRoleDAO().getRole());
            return customerDTO;
        }).collect(Collectors.toList());
    }

    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {
        customerDTO.setRole("GUEST");
        customerDTO.setStatus(Status.ACTIVE);
        CustomerDAO existingCustomer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        BeanUtils.copyProperties(customerDTO, existingCustomer, "customerId", "createDate", "customerAddressDAOList");
        if (customerDTO.getRole() != null) {
            RoleDAO roleDAO = roleRepository.findByRole(customerDTO.getRole()).orElseThrow(() -> new RuntimeException("Role not found"));
            existingCustomer.setRoleDAO(roleDAO);
        }

        if (customerDTO.getCustomerAddressDTOList() != null) {
            existingCustomer.getCustomerAddressDAOList().clear();
            List<CustomerAddressDAO> updatedAddressDAOs = customerDTO.getCustomerAddressDTOList().stream().map(addressDTO -> {
                CustomerAddressDAO addressDAO;
                if (addressDTO.getAddressId() != 0) {
                    addressDAO = customerAddressRepository.findById(addressDTO.getAddressId()).orElseThrow(() -> new RuntimeException("Address not found"));
                } else {
                    addressDAO = new CustomerAddressDAO();
                }
                BeanUtils.copyProperties(addressDTO, addressDAO, "addressId", "createDate");
                addressDAO.setUpdateDate(Instant.now());
                addressDAO.setCustomerDAO(existingCustomer);
                return addressDAO;
            }).collect(Collectors.toList());
            existingCustomer.getCustomerAddressDAOList().addAll(updatedAddressDAOs);
        }

        existingCustomer.setUpdateDate(Instant.now());
        CustomerDAO updatedCustomer = customerRepository.save(existingCustomer);

        CustomerDTO updatedCustomerDTO = new CustomerDTO();
        BeanUtils.copyProperties(updatedCustomer, updatedCustomerDTO);

        if (updatedCustomer.getCustomerAddressDAOList() != null) {
            List<CustomerAddressDTO> addressDTOList = updatedCustomer.getCustomerAddressDAOList().stream().map(addressDAO -> {
                CustomerAddressDTO addressDTO = new CustomerAddressDTO();
                BeanUtils.copyProperties(addressDAO, addressDTO);
                return addressDTO;
            }).collect(Collectors.toList());
            updatedCustomerDTO.setCustomerAddressDTOList(addressDTOList);
        }
        updatedCustomerDTO.setPassword("");
        updatedCustomerDTO.setRole(updatedCustomer.getRoleDAO().getRole());

        return updatedCustomerDTO;
    }

    @Transactional
    public String deleteCustomer(Long customerId) {
        // Find the customer by ID
        CustomerDAO customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer with ID " + customerId + " not found"));

        // Fetch all addresses associated with the customer
        List<CustomerAddressDAO> addresses = customerAddressRepository.findByCustomerDAOCustomerId(customerId);

        // Delete all associated addresses
        addresses.forEach(address -> customerAddressRepository.deleteById(address.getAddressId()));

        // Delete the customer
        customerRepository.deleteById(customerId);

        return AccountManagementConstants.getDeleteMessageFormat(customerId);
    }

    public CustomerDTO getCustomerById(Long customerId) {
        CustomerDAO customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        if (customer.getCustomerAddressDAOList() != null) {
            List<CustomerAddressDTO> addressDTOList = customer.getCustomerAddressDAOList().stream().map(addressDAO -> {
                CustomerAddressDTO addressDTO = new CustomerAddressDTO();
                BeanUtils.copyProperties(addressDAO, addressDTO);
                return addressDTO;
            }).collect(Collectors.toList());
            customerDTO.setCustomerAddressDTOList(addressDTOList);
        }

        customerDTO.setRole(customer.getRoleDAO().getRole());

        return customerDTO;
    }

    public CustomerDTO updateCustomerStatus(Long customerId, Status newStatus) {
        CustomerDAO customerDAO = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        customerDAO.setStatus(newStatus);
        customerDAO.setUpdateDate(Instant.now());

        CustomerDAO updatedCustomerDAO = customerRepository.save(customerDAO);

        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(updatedCustomerDAO, customerDTO);

        if (updatedCustomerDAO.getCustomerAddressDAOList() != null) {
            List<CustomerAddressDTO> addressDTOList = updatedCustomerDAO.getCustomerAddressDAOList().stream().map(addressDAO -> {
                CustomerAddressDTO addressDTO = new CustomerAddressDTO();
                BeanUtils.copyProperties(addressDAO, addressDTO);
                addressDTO.setCustomerId(addressDAO.getCustomerDAO().getCustomerId());
                return addressDTO;
            }).collect(Collectors.toList());
            customerDTO.setCustomerAddressDTOList(addressDTOList);
        }

        customerDTO.setRole(updatedCustomerDAO.getRoleDAO().getRole());

        return customerDTO;
    }
}
