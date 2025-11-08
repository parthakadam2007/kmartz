package com.example.kmartz.services.customer;

import com.example.kmartz.repository.CustomerRepository;
import com.example.kmartz.models.Customer;
import com.example.kmartz.dto.CustomerDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    
    
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;

    }
    
    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow();
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        System.out.println("Raw password: " + customerDTO);
        Customer customer = convertToEntity(customerDTO);
        System.out.println("Raw password: " + customer.getPassword());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        System.out.println("-->f"+customer);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmailNative(email);
        if(customer == null){
            return null; // or throw an exception
        }
        return convertToDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setFirstName(customerDTO.firstName());
        customer.setLastName(customerDTO.lastName());
        customer.setEmail(customerDTO.email());
        customer.setPhoneNo(customerDTO.phoneNo());
        customer.setAddress(customerDTO.address());
        Customer updatedCustomer = customerRepository.save(customer);
        return convertToDTO(updatedCustomer);
    }

    // Convert Customer Entity to CustomerDTO
    private CustomerDTO convertToDTO(Customer customer) {
        return new CustomerDTO(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(),customer.getPassword(), customer.getPhoneNo(), customer.getAddress());
    }

    // Convert CustomerDTO to Customer Entity
    private Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.firstName());
        customer.setLastName(customerDTO.lastName());
        customer.setEmail(customerDTO.email());
        customer.setPassword(customerDTO.password());
        customer.setPhoneNo(customerDTO.phoneNo());
        customer.setAddress(customerDTO.address());
        return customer;
    }
}