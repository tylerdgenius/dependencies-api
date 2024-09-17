package com.metrobuzz.dependencies.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrobuzz.dependencies.exceptions.ResourceNotFoundException;
import com.metrobuzz.dependencies.models.CityModel;
import com.metrobuzz.dependencies.services.CityService;

@RestController
@RequestMapping("/cities")
public class CitiesController {
    private CityService cityService;

    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/by-state-code/{stateCode}")
    public List<CityModel> getCitiesByStateCode(@PathVariable String stateCode) {
        return cityService.getCitiesByStateCode(stateCode);
    }

    @GetMapping("/by-country-code/{countryCode}")
    public List<CityModel> getCitiesByCountryCode(@PathVariable String countryCode) {
        return cityService.getCitiesByCountryCode(countryCode);
    }

    @GetMapping("/single/{id}")
    public CityModel getCity(@PathVariable String id) {
        CityModel city = cityService.getCityById(id);

        if (city == null) {
            throw new ResourceNotFoundException("Unable to find city");
        }

        return city;
    }

    @GetMapping("/all")
    public List<CityModel> getCountries() {
        return cityService.getAllCities();
    }
}
