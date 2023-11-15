package com.example.eatery.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class UpdateEateryRequest {
    private String code;
    private String category;
    private String name;
    private String location;
    private String address;
    private String detail;
}
