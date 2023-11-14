package com.example.Stay.Entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter

public class LocationCode {
    @Id
    @Column(name="location_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String location_code;
    private String name;

    public LocationCode() {
        this.location_code = location_code;
        this.name = name;
    }

}
