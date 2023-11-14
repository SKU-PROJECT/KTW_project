package com.example.Stay.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name="stayCode")
    private String stayCode;
    @Column(name="name")
    private String name;
    @Column(name="category")
    private String category;
    @Column(name="price")
    private String price;
    @Column(name="locationCode")
    private String locationCode;
    @Column(name="address")
    private String address;
    @Column(name="service")
    private String service;
    @Column(name="useGuide")
    private String useGuide;


    @Builder
    public Stay() {
        this.stayCode = stayCode;
        this.name = name;
        this.category = category;
        this.price = price;
        this.locationCode = locationCode;
        this.address = address;
        this.service = service;
        this.useGuide = useGuide;

    }


}
