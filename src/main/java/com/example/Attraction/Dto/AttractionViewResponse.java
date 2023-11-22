package com.example.Attraction.Dto;

import com.example.Attraction.Entity.Attraction;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AttractionViewResponse {

    private Long id;
    private String name;
    private String category;
    private String location;
    private String address;
    private String detail;

    public AttractionViewResponse(Attraction attraction){

        this.id = attraction.getId();
        this.name= attraction.getName();
        this.category = attraction.getCategory();
        this.location = attraction.getLocation();
        this.address = attraction.getAddress();
        this.detail = attraction.getDetail();

    }
}