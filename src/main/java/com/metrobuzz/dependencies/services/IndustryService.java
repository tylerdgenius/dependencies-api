package com.metrobuzz.dependencies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metrobuzz.dependencies.models.IndustryModel;
import com.metrobuzz.dependencies.repositories.IndustryRepository;

@Service
public class IndustryService {

    private IndustryRepository industryRepository;

    @Autowired
    public IndustryService(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    public IndustryModel getIndustryById(String id) {
        return industryRepository.findById(id).orElse(null);
    }

    public List<IndustryModel> getAllIndustries() {
        return industryRepository.findAll();
    }

    public IndustryModel getIndustryByName(String name) {
        return industryRepository.findByName(name);
    }
}
