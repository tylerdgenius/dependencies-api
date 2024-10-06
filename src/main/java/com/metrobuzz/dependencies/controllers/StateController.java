package com.metrobuzz.dependencies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrobuzz.dependencies.models.StateModel;
import com.metrobuzz.dependencies.services.StateService;

@RestController
@RequestMapping("/api/states")
public class StateController {
    private StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @GetMapping("/all")
    public List<StateModel> getAllStates() {
        return stateService.getStates();
    }

    @GetMapping("/by-country-code/{countryCode}")
    public List<StateModel> getStatesByCountryCode(@PathVariable String countryCode) {
        return stateService.getStatesByCountryCode(countryCode);
    }
}
