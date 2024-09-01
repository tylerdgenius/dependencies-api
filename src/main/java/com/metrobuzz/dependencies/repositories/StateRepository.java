package com.metrobuzz.dependencies.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.metrobuzz.dependencies.models.StateModel;

public interface StateRepository extends MongoRepository<StateModel, String> {
    List<StateModel> findStatesByCountryCode(String countryCode);
}
