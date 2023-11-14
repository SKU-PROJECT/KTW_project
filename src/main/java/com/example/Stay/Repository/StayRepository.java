package com.example.Stay.Repository;

import com.example.Stay.Entity.Stay;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StayRepository extends JpaRepository<Stay, Long> {
  //  boolean existsByName(String name);
}
