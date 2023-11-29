package com.example.Cart.Entity;

import com.example.Stay.Entity.Stay;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter@Setter
@Table(name="cart_item")
public class CartItem {

    @Id
    @GeneratedValue
    @Column(name="cart_item_id")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY) //하나의 장바구니에 여러개의 숙소를 담을 수 있음
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne(fetch= FetchType.LAZY) //장바구니에 담을 숙소의 정보를 stay_id를 통해 매핑
    @JoinColumn(name="stay_id")
    private Stay stay;

    private int count;


}
