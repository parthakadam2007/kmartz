package com.example.kmartz.services.customer;
import java.util.List;

import com.example.kmartz.dto.CustomerDTO;
public interface CustomerService {
    public List<CustomerDTO> getAllCustomers();
    public CustomerDTO saveCustomer(CustomerDTO customerDTO);
    public CustomerDTO getCustomerById(Long id);
    public CustomerDTO getCustomerByEmail(String email);
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);
}
