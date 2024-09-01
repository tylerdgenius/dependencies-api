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
    public Response<List<CountryModel>> getCountries() {
        List<CountryModel> countries = countryService.getAllCountries();

        return new Response<>("Success", HttpStatus.OK.value(), countries);
    }

    @GetMapping("/single/{id}")
    public Response<CountryModel> getCountry(@PathVariable String id) {
        CountryModel country = countryService.getCountryById(id);

        return new Response<>("Success", HttpStatus.OK.value(), country);
    }
}
