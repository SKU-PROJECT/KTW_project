package com.example.Trip.Dto;

import com.example.Trip.Entity.Trip;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TripFormDto {   //여행지 데이터 정보를 전달하는 DTO

    private Long id;   //여행지 코드

    @NotBlank(message = "여행지명은 필수 입력 값입니다.")
    private String name;

    private String detail;   //상세정보
    private String address;     //여행지 주소
    private String telephone ;  //여행지 문의 및 전화
    private String link ;   //여행지 링크
    private String parking ;    // 주차 정보
    private String service;  //서비스

    private List<TripImgDto> tripImgDtoList = new ArrayList<>();

    private List<Long> tripImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Trip createTrip(){
        return modelMapper.map(this, Trip.class);
    }

    public static TripFormDto of(Trip trip){
        return modelMapper.map(trip,TripFormDto.class);
    }
}
