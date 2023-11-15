package com.example.Eatery.Dto;

import com.example.Eatery.Entity.Eatery;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EateryViewResponse {
    private Long id;
    private String code;
    private String category;
    private String name;
    private String location;
    private String address;
    private String detail;

    public EateryViewResponse(Eatery eatery){
        this.id=eatery.getId();
        this.code=eatery.getCode();
        this.category=eatery.getCategory();
        this.name=eatery.getName();
        this.location=eatery.getLocation();
        this.address=eatery.getAddress();
        this.detail=eatery.getDetail();
    }
}