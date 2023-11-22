package com.example.Attraction.Repository;


import com.example.Attraction.Entity.Attraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    // 페이징
    Page<Attraction> findAll(Pageable pageable);
}
