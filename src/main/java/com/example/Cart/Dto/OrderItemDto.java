package com.example.Cart.Dto;

import com.example.Cart.Entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {

    public OrderItemDto(OrderItem orderItem ){
        this.name  = orderItem.getStay().getName();
        this.count = orderItem.getCount();
        this.orderPrice = orderItem.getOrderPrice();
//        this.imgUrl = imgUrl;
    }

    private String name; //숙소명
    private int count; //숙박 일수

    private int orderPrice; //결제 금액
//    private String imgUrl; //상품 이미지 경로

}
