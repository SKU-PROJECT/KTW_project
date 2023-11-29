package com.example.Stay.dto;

import com.example.Stay.Constant.StaySellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaySearchDto {

    private String searchDateType;
    private StaySellStatus searchSellStatus; // 현재 숙소의 예약 상태를 기준으로 숙소 데이터를 조회
    private String searchBy; //숙소를 조회할때 어떤 유형으로 조회할지 선택
    private String searchQuery= "";  // 조회할 검색어를 저장할 변수
}
