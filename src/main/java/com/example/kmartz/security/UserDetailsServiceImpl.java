package com.example.kmartz.security;

import com.example.kmartz.repository.CustomerRepository;
import com.example.kmartz.models.Customer;
import com.example.kmartz.models.UserDetailModel;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmailNative(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailModel(customer);
    }
}
