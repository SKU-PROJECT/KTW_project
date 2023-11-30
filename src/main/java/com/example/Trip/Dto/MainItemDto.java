package com.example.Trip.Dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainItemDto {
    private Long id;
    private String name;   //여행지명
    private String imgUrl;   //이미지 url

    private String address;  //주소

    @QueryProjection
    public MainItemDto(Long id, String name, String address, String imgUrl) {
        this.id= id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.address = address;

    }
}
