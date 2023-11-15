package com.example.Eatery.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="eatery")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Eatery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false) // PK
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false) // FK
    private String location;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "detail", nullable = true)
    private String detail;

    @Builder
    public Eatery(String code, String category, String name, String location,
                  String address, String detail) {
        this.code = code;
        this.category = category;
        this.name=name;
        this.location = location;
        this.address = address;
        this.detail = detail;
    }

    public void update(String code, String category, String name, String location,
                       String address, String detail){
        this.code=code;
        this.category=category;
        this.name=name;
        this.location=location;
        this.address=address;
        this.detail=detail;
    }
}
