package com.example.Eatery.Dto;

import com.example.Eatery.Entity.Eatery;
import lombok.Getter;

@Getter
public class EateryResponse {

    //    private final Long id;
    private final String code;
    private final String category;
    private final String name;
    private final String location;
    private final String address;
    private final String detail;

    public EateryResponse(Eatery eatery){

//        this.id=eatery.getId();
        this.code=eatery.getCode();
        this.category=eatery.getCategory();
        this.name=eatery.getName();
        this.location=eatery.getLocation();
        this.address= eatery.getAddress();
        this.detail=eatery.getDetail();
    }
}