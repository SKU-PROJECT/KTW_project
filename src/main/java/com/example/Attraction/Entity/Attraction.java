package com.example.Attraction.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="attraction")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false) // PK
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="category", nullable = false)
    private String category;

    @Column(name="location", nullable = false)
    private String location;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="detail", nullable = true)
    private String detail;

    @Builder
    public Attraction(String name, String category, String location,
                      String address, String detail){

        this.name=name;
        this.category=category;
        this.location=location;
        this.address=address;
        this.detail=detail;
    }

    public void update(String name, String category, String location,
                       String address, String detail){

        this.name=name;
        this.category=category;
        this.location=location;
        this.address=address;
        this.detail=detail;
    }
}
