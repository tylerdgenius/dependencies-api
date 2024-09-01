package com.metrobuzz.dependencies.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.metrobuzz.dependencies.models.CityModel;

public interface CityRepository extends MongoRepository<CityModel, String> {
    List<CityModel> findByStateCode(String stateCode);
    
    List<CityModel> findByCountryCode(String countryCode);

    CityModel findByName(String name);
}
