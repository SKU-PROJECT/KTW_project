package com.example.Stay.Repository;


import com.example.Stay.Entity.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StayRepository extends JpaRepository<Stay, Long>, QuerydslPredicateExecutor<Stay> {
//    List<Stay> findByName(String name);
    @Query("select s from Stay s where s.category like %:category% order by s.price desc")
    List<Stay> findByCategory(@Param("category") String category);
}
