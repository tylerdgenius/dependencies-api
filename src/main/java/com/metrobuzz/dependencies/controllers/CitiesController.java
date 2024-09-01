package com.metrobuzz.dependencies.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrobuzz.dependencies.models.CityModel;
import com.metrobuzz.dependencies.services.CityService;
import com.metrobuzz.dependencies.utilities.Response;

@RestController
@RequestMapping("/cities")
public class CitiesController {
    private CityService cityService;

    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/by-state-code/{stateCode}")
    public Response<List<CityModel>> getCitiesByStateCode(@PathVariable String stateCode) {
        List<CityModel> cities = cityService.getCitiesByStateCode(stateCode);

        return new Response<>("Success", HttpStatus.OK.value(), cities);
    }

    @GetMapping("/by-country-code/{countryCode}")
    public Response<List<CityModel>> getCitiesByCountryCode(@PathVariable String countryCode) {
        List<CityModel> cities = cityService.getCitiesByCountryCode(countryCode);

        return new Response<>("Success", HttpStatus.OK.value(), cities);
    }

    @GetMapping("/single/{id}")
    public Response<CityModel> getCity(@PathVariable String id) {
        CityModel city = cityService.getCityById(id);

        return new Response<>("Success", HttpStatus.OK.value(), city);
    }

    @GetMapping("/all")
    public Response<List<CityModel>> getCountries() {
        List<CityModel> cities = cityService.getAllCities();

        return new Response<>("Success", HttpStatus.OK.value(), cities);
    }
}
