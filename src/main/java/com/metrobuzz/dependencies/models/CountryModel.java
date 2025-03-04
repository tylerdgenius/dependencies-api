package com.metrobuzz.dependencies.models;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "countries")
public class CountryModel {
    @Id
    public String id;
    private String name;
    private String isoCode;
    private String phonecode;
    private String flag;
    private String currency;
    private String latitude;
    private String longitude;
    private List<TimeZone> timezones;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
