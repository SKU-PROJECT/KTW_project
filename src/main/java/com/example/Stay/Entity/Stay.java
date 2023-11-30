package com.example.Stay.Entity;

import com.example.Order.Exception.OutOfStockException;
import com.example.Stay.Constant.StaySellStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Table (name="stay")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Stay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stay_id")
    private Long stay_id;   //숙소코드
    @Column(nullable = false, length=100)
    private String name;     //숙소명
    @Column(name="category")
    private String category;   //숙소카테고리
    @Column(name="price" ,nullable = false)
    private int price;           //가격
    @Column(nullable = false)
    private int stayday;  // 객실수
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String detail;          //숙소상세설명
    @Column
    private String address;         //숙소위치
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String service;         //숙소 서비스내용
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String use_guide;       //이용안내
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String amenity;         //편의시설

    @Enumerated(EnumType.STRING)
    private StaySellStatus staySellStatus;

    @CreatedDate
    @Column(name = "reg_time", updatable = false)
    private LocalDateTime regTime;  //등록시간

    @LastModifiedDate
    @Column(name = "update_time")
    private LocalDateTime updateTime;       //수정시간




    public void removeStay(int stayday) {
        int restStay = this.stayday - stayday;
        if(restStay<0) {
            throw new OutOfStockException("객실이 모두 예약 마감되었습니다. (현재 객실 수: " + this.stayday + ")");
        }
        this.stayday = restStay;
    }

    @Builder
    public Stay(
            String name,
            String category,
            int price,
            int stayday,
            String detail,
            String address,
            String service,
            String use_guide,
            String amenity,
            StaySellStatus staySellStatus
    ) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stayday = stayday;
        this.detail = detail;
        this.address = address;
        this.service = service;
        this.use_guide = use_guide;
        this.amenity = amenity;
        this.staySellStatus = staySellStatus;
    }
}
