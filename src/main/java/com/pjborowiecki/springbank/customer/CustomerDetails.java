package com.pjborowiecki.springbank.customer;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
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

        String userName;
        String password;
        List<GrantedAuthority> authorities = new ArrayList<>();

        Customer customer = customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        userName = customer.getEmail();
        password = customer.getPassword();
        authorities.add(new SimpleGrantedAuthority(customer.getRole()));

        return new User(userName, password, authorities);
    }

}
