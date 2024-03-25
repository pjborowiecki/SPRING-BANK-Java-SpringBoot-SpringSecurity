package com.pjborowiecki.springbank.auth;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pjborowiecki.springbank.customer.Customer;
import com.pjborowiecki.springbank.customer.CustomerRequest;
import com.pjborowiecki.springbank.customer.CustomerRepository;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<Customer> signup(@RequestBody CustomerRequest newCustomerRequest, UriComponentsBuilder ucb) {
        Customer newCustomer = Customer.builder()
                .email(newCustomerRequest.email())
                .password(newCustomerRequest.password())
                .role(newCustomerRequest.role())
                .build();

        String passordHash = this.passwordEncoder.encode(newCustomer.getPassword());
        newCustomer.setPassword(passordHash);

        Customer savedCustomer = this.customerRepository.save(newCustomer);
        URI savedCustomerLocation = ucb.path("/api/v1/customer/{id}").buildAndExpand(savedCustomer.getId()).toUri();
        return ResponseEntity.created(savedCustomerLocation).body(savedCustomer);
    }

}
