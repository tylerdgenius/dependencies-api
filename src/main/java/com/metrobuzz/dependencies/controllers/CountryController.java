package com.metrobuzz.dependencies.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrobuzz.dependencies.exceptions.ResourceNotFoundException;
import com.metrobuzz.dependencies.models.CountryModel;
import com.metrobuzz.dependencies.services.CountryService;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public List<CountryModel> getCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/single/{id}")
    public CountryModel getCountry(@PathVariable String id) {
        CountryModel country = countryService.getCountryById(id);

        if (country == null) {
            throw new ResourceNotFoundException("Unable to find this country");
        }

        return country;
    }
}
