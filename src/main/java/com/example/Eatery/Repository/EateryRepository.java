package com.example.Eatery.Repository;

import com.example.Eatery.Entity.Eatery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EateryRepository extends JpaRepository<Eatery, Long> {
    // 페이징
    Page<Eatery> findAll(Pageable pageble);
}
