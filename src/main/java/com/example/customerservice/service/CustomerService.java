package com.example.customerservice.service;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.entity.Country;
import com.example.customerservice.model.CountryDTO;
import com.example.customerservice.model.CustomerResponse;
import com.example.customerservice.repository.CustomerRepository;
import com.example.customerservice.model.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CountryService countryService;

    // Injecting CustomerRepository and CountryService
    @Autowired
    public CustomerService(CustomerRepository customerRepository, CountryService countryService) {
        this.customerRepository = customerRepository;
        this.countryService = countryService;
    }

    // Create a new customer
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        // Fetch the country object using CountryService based on the country name
        Optional<Country> countryOptional = countryService.getCountryByCode(customerRequest.getCountry());

        // If country doesn't exist, throw an exception or return a response
        if (countryOptional.isEmpty()) {
            throw new RuntimeException("Country not found: " + customerRequest.getCountry());
        }

        Customer customer = getCustomer(customerRequest, countryOptional);

        // Call repository to save the customer
        customer  = customerRepository.save(customer);
        CustomerResponse response = new CustomerResponse();
        response.setCustomerId(customer.getCustomerId());
        response.setName(customer.getName());
        response.setPrimaryEmail(customer.getPrimaryEmail());
        response.setPrimaryPhone(customer.getPrimaryPhone());
        response.setState(customer.getState());
        response.setPincode(customer.getPincode());

        CountryDTO countryDto = new CountryDTO();
        countryDto.setCountryCode(customerRequest.getCountry());
        countryDto.setCountryName(countryOptional.get().getCountryName());

        response.setCountry(countryDto);

        return response;
    }

    private static Customer getCustomer(CustomerRequest customerRequest, Optional<Country> countryOptional) {
        Country country = countryOptional.get();
        // Create a new Customer entity
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setPrimaryEmail(customerRequest.getPrimaryEmail());
        customer.setSecondaryEmail(customerRequest.getSecondaryEmail());
        customer.setPrimaryPhone(customerRequest.getPrimaryPhone());
        customer.setSecondaryPhone(customerRequest.getSecondaryPhone());
        customer.setState(customerRequest.getState());
        customer.setCountry(country);
        customer.setPincode(customerRequest.getPinCode());
        return customer;
    }

    // Update an existing customer
    public Customer updateCustomer(int customerId, CustomerRequest customerRequest) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            Customer existingCustomer = customerOptional.get();

            // Fetch the country object using CountryService
            Optional<Country> countryOptional = countryService.getCountryByName(customerRequest.getCountry());
            if (countryOptional.isEmpty()) {
                throw new RuntimeException("Country not found: " + customerRequest.getCountry());
            }
            Country country = countryOptional.get();

            // Update existing customer fields
            existingCustomer.setName(customerRequest.getName());
            existingCustomer.setPrimaryEmail(customerRequest.getPrimaryEmail());
            existingCustomer.setSecondaryEmail(customerRequest.getSecondaryEmail());
            existingCustomer.setPrimaryPhone(customerRequest.getPrimaryPhone());
            existingCustomer.setSecondaryPhone(customerRequest.getSecondaryPhone());
            existingCustomer.setState(customerRequest.getState());
            existingCustomer.setCountry(country); // Update country
            existingCustomer.setPincode(customerRequest.getPinCode());
            existingCustomer.setUpdatedAt(existingCustomer.getUpdatedAt()); // Set the updated timestamp

            // Save the updated customer
            return customerRepository.save(existingCustomer);
        }

        return null;  // or throw exception if customer doesn't exist
    }

    public Optional<Customer> getCustomerByEmailAndPhone(String email, String phone) {
        return customerRepository.findByPrimaryEmailAndPrimaryPhone(email, phone);
    }
}
