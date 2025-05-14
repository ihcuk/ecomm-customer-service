package com.example.customerservice.controller;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.model.CustomerRequest;
import com.example.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Create customer if not exists
    @PostMapping
    @Validated
    public ResponseEntity<?> createCustomerIfNotExists(@RequestBody CustomerRequest customerRequest) {
        Optional<Customer> optionalCustomer = customerService
                .getCustomerByEmailAndPhone(customerRequest.getPrimaryEmail(), customerRequest.getPrimaryPhone());

        if (optionalCustomer.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Customer with email and primary phone already exists: " + customerRequest.getPrimaryEmail());
        }
        return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }
}
