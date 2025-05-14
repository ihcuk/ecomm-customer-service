package com.example.customerservice.repository;

import com.example.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByPrimaryEmail(String primaryEmail);

    Optional<Customer> findByPrimaryPhone(String primaryPhone);

    Optional<Customer> findByPrimaryEmailAndPrimaryPhone(String email, String phone);
}
