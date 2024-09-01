package com.metrobuzz.dependencies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.metrobuzz.dependencies.services.CountryService;
import com.metrobuzz.dependencies.models.CountryModel;

@RestController
public class CountryController {
    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    public List<CountryModel> getCountries() {
        return countryService.getAllCountries();
    }

}
