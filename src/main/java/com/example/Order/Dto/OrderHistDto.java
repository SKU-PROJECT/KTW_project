package com.example.Order.Dto;

import com.example.Order.Constant.OrderStatus;
import com.example.Order.Entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderHistDto {

    public OrderHistDto(Order order){
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }

    private Long orderId; //예약아이디
    private String orderDate; //예약날짜
    private OrderStatus orderStatus; //예약 상태

    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    //숙소 예약리스트
    public void addOrderItemDto(OrderItemDto orderItemDto){
        orderItemDtoList.add(orderItemDto);
    }

}