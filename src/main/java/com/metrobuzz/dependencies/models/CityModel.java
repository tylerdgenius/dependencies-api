package com.metrobuzz.dependencies.models;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "cities")
public class CityModel {
    @Id
    private String id;
    private String name;
    private String stateCode;
    private String countryCode;
    private String latitude;
    private String longitude;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
