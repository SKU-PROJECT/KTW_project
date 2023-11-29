package com.example.Trip.Dto;

import com.example.Trip.Entity.Trip;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TripDto {

    private Long id;  //여행지 코드

    private String name;  //여행지 이름

    private String detail; //여행지 상세 정보

    private String address; //여행지 주소

    private String telephone ; //여행지 문의 및 전화

    private String link ; //여행지 링크

    private String parking ; // 주차 정보

    private String service;  //서비스

    private String imgUrl;   //이미지 url


    public TripDto(Trip trip) {
        this.id = trip.getId();
        this.name = trip.getName();
        this.detail = trip.getDetail();
        this.address = trip.getAddress();
        this.service = trip.getService();
        this.link = trip.getLink();
        this.parking = trip.getParking();
        this.telephone = trip.getTelephone();
    }


}
