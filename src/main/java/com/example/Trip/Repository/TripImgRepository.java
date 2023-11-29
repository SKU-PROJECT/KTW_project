package com.example.Trip.Repository;

import com.example.Trip.Entity.TripImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripImgRepository extends JpaRepository<TripImg, Long> {
    List<TripImg> findByTrip_IdOrderByIdAsc(Long id);

}