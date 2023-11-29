package com.example.Trip.Dto;

import com.example.Trip.Entity.TripImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class TripImgDto {
    //여행지 이미지 데이터를 전달할 DTO
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static TripImgDto of(TripImg tripImg) {
        return modelMapper.map(tripImg,TripImgDto.class);
    }

}