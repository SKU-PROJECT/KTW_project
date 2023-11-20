package com.example.Stay.dto;


import com.example.Stay.Entity.Stay;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class StayResponse {

    private Long id;
    private String stayCode;
    private String name;
    private String category;
    private String price;
    private String locationCode;
    private String address;
    private String service;
    private String useGuide;

    private String amenity;


    public StayResponse(Stay stay) {
        this.id = stay.getId();
        this.stayCode = stay.getStayCode();
        this.name = stay.getName();
        this.category = stay.getCategory();
        this.price = stay.getPrice();
        this.locationCode = stay.getLocationCode();
        this.address = stay.getAddress();
        this.service = stay.getService();
        this.useGuide = stay.getUseGuide();
        this.amenity = stay.getAmenity();

    }

}
