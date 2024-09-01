package com.metrobuzz.dependencies.services;

import com.metrobuzz.dependencies.repositories.StateRepository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.metrobuzz.dependencies.models.StateModel;

@Service
public class StateService {

    private StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public List<StateModel> getStatesByCountryCode (String countryCode) {
        return this.stateRepository.findStatesByCountryCode(countryCode);
    }

    public List<StateModel> getStates() {
        return this.stateRepository.findAll();
    }
}
