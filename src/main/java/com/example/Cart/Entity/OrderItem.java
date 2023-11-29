package com.example.Cart.Entity;


import com.example.Stay.Entity.Stay;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name="order_stay_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="stay_id")
    private Stay stay;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice;  //예약가격

    private int count;      //숙박일수
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    public static OrderItem createOrderItem(Stay stay, int count) {
        OrderItem orderItem = new OrderItem(); // Fix: instantiate OrderItem here
        orderItem.setStay(stay); // Fix: use the correct method to set Stay
        orderItem.setCount(count);
        orderItem.setOrderPrice(stay.getPrice());

        stay.removeStay(count);
        return orderItem;
    }

    public int getTotalPrice(){
        return orderPrice*count;
    }

}
