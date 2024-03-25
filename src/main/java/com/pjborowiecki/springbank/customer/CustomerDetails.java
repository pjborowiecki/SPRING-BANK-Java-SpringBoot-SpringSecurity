package com.pjborowiecki.springbank.customer;

import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class CustomerDetails implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerDetails(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User
                .withUsername(customer.getEmail())
                .password(customer.getPassword())
                .authorities(new SimpleGrantedAuthority(customer.getRole()))
                .build();
    }

}
