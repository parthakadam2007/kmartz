package com.example.kmartz.controller;

import com.example.kmartz.models.JwtRequest;
import com.example.kmartz.models.JwtResponse;
import com.example.kmartz.security.JWTHelper;
import com.example.kmartz.dto.CustomerDTO;
import com.example.kmartz.services.customer.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final CustomerService customerService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JWTHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer/signup")
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        if(customerDTO.email() == null || customerDTO.email().isEmpty() || customerDTO.password() == null || customerDTO.password().isEmpty()){
            throw new RuntimeException("Email cannot be null or empty");
        }
        if(customerService.getCustomerByEmail(customerDTO.email()) != null){
            throw  new RuntimeException("Customer with email "+customerDTO.email()+" already exists");
        }
        System.out.println("Creating customer: " + customerDTO);
        return customerService.saveCustomer(customerDTO);
    }

    @PostMapping("/customer/login") 
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());

        Long customerId = customerService.getCustomerByEmail(request.getEmail()).customerId();
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails,customerId);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
    UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(email, password);
    try {
        manager.authenticate(authentication);
    } catch (BadCredentialsException e) {
        throw new BadCredentialsException(" Invalid email or Password  !!");
    }
    }
     

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
