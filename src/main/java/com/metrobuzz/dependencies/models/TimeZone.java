package com.metrobuzz.dependencies.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "timezones")
public class TimeZone {

    @Id
    private String id;

    private String zoneName;
    private int gmtOffset;
    private String gmtOffsetName;
    private String abbreviation;
    private String tzName;

}
