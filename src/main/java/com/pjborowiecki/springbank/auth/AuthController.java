package com.pjborowiecki.springbank.auth;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import com.pjborowiecki.springbank.customer.Customer;
import com.pjborowiecki.springbank.customer.CustomerRequest;
import com.pjborowiecki.springbank.customer.CustomerRepository;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final CustomerRepository customerRepository;

    public AuthController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<Customer> signup(@RequestBody CustomerRequest newCustomerRequest, UriComponentsBuilder ucb) {
        Customer customer = new Customer(null,
                newCustomerRequest.email(),
                newCustomerRequest.password(),
                newCustomerRequest.role());

        // Passowrd hasing

        Customer newCustomer = this.customerRepository.save(customer);
        URI newCustomerLocation = ucb.path("/api/v1/customers/{id}").buildAndExpand(newCustomer.getId()).toUri();
        return ResponseEntity.created(newCustomerLocation).body(newCustomer);
    }

}
