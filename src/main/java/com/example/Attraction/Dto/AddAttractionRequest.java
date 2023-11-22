package com.example.Attraction.Dto;

import com.example.Attraction.Entity.Attraction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddAttractionRequest {

    private String category;
    private String name;
    private String location;
    private String address;
    private String detail;

    public Attraction toEntity(){

        return Attraction.builder()
                .category(category)
                .name(name)
                .location(location)
                .address(address)
                .detail(detail)
                .build();
    }
}
