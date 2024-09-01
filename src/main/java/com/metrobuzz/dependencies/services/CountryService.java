package com.metrobuzz.dependencies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metrobuzz.dependencies.models.CountryModel;
import com.metrobuzz.dependencies.repositories.CountryRepository;

@Service
public class CountryService {

    private CountryRepository countryRepository;
    
    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CountryModel geCountryById(String id) {
       return countryRepository.findById(id).orElse(null);
    }

    public List<CountryModel> getAllCountries() {
        return countryRepository.findAll();
    }

}
