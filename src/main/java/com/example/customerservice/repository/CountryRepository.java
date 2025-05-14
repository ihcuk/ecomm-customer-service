package com.example.customerservice.repository;

import com.example.customerservice.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, String> {
    Optional<Country> findByCountryName(String countryName);
    Optional<Country> findByCountryCode(String countryCode);
}