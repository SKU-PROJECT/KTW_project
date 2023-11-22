package com.example.Stay.dto;


import com.example.Stay.Entity.Stay;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class StayDto {

    private Long id;
    private String detail;
    private String name;
    private String category;
    private String price;
    private String address;
    private String service;
    private String useGuide;

    private String amenity;

    @Builder
    public StayDto(Stay stay) {
        this.id = stay.getId();
        this.detail = stay.getDetail();
        this.name = stay.getName();
        this.category = stay.getCategory();
        this.price = stay.getPrice();
        this.address = stay.getAddress();
        this.service = stay.getService();
        this.useGuide = stay.getUseGuide();
        this.amenity = stay.getAmenity();

    }

}
