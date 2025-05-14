package com.example.customerservice.service;

import com.example.customerservice.entity.Country;
import com.example.customerservice.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    // Find a country by name
    public Optional<Country> getCountryByName(String countryName) {
        return countryRepository.findByCountryName(countryName);
    }

    // Find a country by country code
    public Optional<Country> getCountryByCode(String countryCode) {
        return countryRepository.findByCountryCode(countryCode);
    }
}
