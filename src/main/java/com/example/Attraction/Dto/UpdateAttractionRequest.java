package com.example.Attraction.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class UpdateAttractionRequest {

    private String category;
    private String name;
    private String location;
    private String address;
    private String detail;

}