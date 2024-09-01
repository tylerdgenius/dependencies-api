package com.metrobuzz.dependencies.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrobuzz.dependencies.models.IndustryModel;
import com.metrobuzz.dependencies.services.IndustryService;
import com.metrobuzz.dependencies.utilities.Response;

@RestController
@RequestMapping("/industries")
public class IndustriesController {
    private IndustryService industryService;

    public IndustriesController(IndustryService industryService) {
        this.industryService = industryService;
    }

    public Response<List<IndustryModel>> getAllIndustries() {
        List<IndustryModel> industries = industryService.getAllIndustries();

        return new Response<>("Success", HttpStatus.OK.value(), industries);
    }

    public Response<IndustryModel> getIndustry(@PathVariable String id) {
        IndustryModel industry = industryService.getIndustryById(id);

        return new Response<>("Success", HttpStatus.OK.value(), industry);
    }
}
