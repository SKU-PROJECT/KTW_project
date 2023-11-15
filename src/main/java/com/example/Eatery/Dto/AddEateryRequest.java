package com.example.Eatery.Dto;

import com.example.Eatery.Entity.Eatery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddEateryRequest {
    //    private Long id;
    private String code;
    private String category;
    private String name;
    private String location;
    private String address;
    private String detail;

    public Eatery toEntity(){
        return Eatery.builder()
//                .id(id)
                .code(code)
                .category(category)
                .name(name)
                .location(location)
                .address(address)
                .detail(detail)
                .build();
    }

}
