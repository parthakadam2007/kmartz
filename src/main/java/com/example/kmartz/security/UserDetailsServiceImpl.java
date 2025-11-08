package com.example.kmartz.security;

import com.example.kmartz.repository.CustomerRepository;
import com.example.kmartz.models.Customer;
import com.example.kmartz.models.ShopKeeper;
import com.example.kmartz.models.UserDetailModel;
import com.example.kmartz.repository.ShopKeeperRepository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShopKeeperRepository shopKeeperRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmailNative(username);
        if (customer == null) {
            // throw new UsernameNotFoundException("User not found");
            System.out.println("Customer not found with email:res " + username);
            ShopKeeper shopKeeper = shopKeeperRepository.findByEmailNative(username);
            if (shopKeeper == null) {
                throw new UsernameNotFoundException("User not found");
            } else {
                return new UserDetailModel(shopKeeper);
            }

        }
        return new UserDetailModel(customer);
    }
}
