package com.metrobuzz.dependencies.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.metrobuzz.dependencies.services.CountryService;
import com.metrobuzz.dependencies.utilities.Response;
import com.metrobuzz.dependencies.models.CountryModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/countries")
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
        return countryService.getCountryById(id);
    }
}
