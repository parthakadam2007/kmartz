package com.example.kmartz.controller;

import com.example.kmartz.dto.CustomerDTO;
import com.example.kmartz.security.JWTHelper;
import com.example.kmartz.services.customer.CustomerService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/customers")
public class CustomersController {
    private final CustomerService customerService;
    @Autowired
    private JWTHelper jwtHelper;
    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Define API endpoints here
    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customerId/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        System.out.println("Fetching customer with ID: " + id);
        return customerService.getCustomerById(id);
    }
    @GetMapping("/test")
    public CustomerDTO test(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header"); 
        }

        String token = authHeader.substring(7);
        Long userId = jwtHelper.getUserIdFromToken(token);

        System.out.println("Fetching all customers");
        return customerService.getCustomerById(userId);
    }
    @GetMapping("/email/{email}")
    public CustomerDTO getCustomerByEmail(@PathVariable String email) {
        System.out.println("Fetching customer with email: " + email);
        return customerService.getCustomerByEmail(email);
    }
    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        System.out.println("Creating customer: " + customerDTO);
        return customerService.saveCustomer(customerDTO);
    }
    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        System.out.println("Updating customer with ID: " + id + " with data: " + customerDTO);
        return customerService.updateCustomer(id, customerDTO);
    }
}