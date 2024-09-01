package com.metrobuzz.dependencies.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.metrobuzz.dependencies.models.IndustryModel;

public interface IndustryRepository extends MongoRepository<IndustryModel, String> {
    IndustryModel findByName(String name);
}
