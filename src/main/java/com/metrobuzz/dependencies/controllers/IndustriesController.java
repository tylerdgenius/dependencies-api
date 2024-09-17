package com.metrobuzz.dependencies.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrobuzz.dependencies.exceptions.ResourceNotFoundException;
import com.metrobuzz.dependencies.models.IndustryModel;
import com.metrobuzz.dependencies.services.IndustryService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/industries")
public class IndustriesController {
    private IndustryService industryService;

    public IndustriesController(IndustryService industryService) {
        this.industryService = industryService;
    }

    @GetMapping("/all")
    public List<IndustryModel> getAllIndustries() {
        return industryService.getAllIndustries();
    }

    @GetMapping("/single/{id}")
    public IndustryModel getIndustry(@PathVariable String id) throws ResourceNotFoundException {
        IndustryModel industry = industryService.getIndustryById(id);

        if (industry == null) {
            throw new ResourceNotFoundException("Unable to find this industry");
        }

        return industry;
    }
}
