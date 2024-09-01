package com.metrobuzz.dependencies.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.metrobuzz.dependencies.models.CountryModel;

public interface CountryRepository extends MongoRepository<CountryModel, String> {

}
