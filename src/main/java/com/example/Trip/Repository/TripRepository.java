package com.example.Trip.Repository;

import com.example.Trip.Entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TripRepository extends JpaRepository<Trip, Long>, QuerydslPredicateExecutor<Trip>, TripRepositoryCustom{
//    @Query("SELECT t FROM Trip t WHERE t.address LIKE CONCAT('%', :query, '%') OR t.name LIKE CONCAT('%', :query, '%')")
//    Page<Trip> findByAddressOrName(@Param("query") String query, Pageable pageable);
//

}

//public interface TripRepository extends JpaRepository<Trip, Long>, QuerydslPredicateExecutor<Trip>, TripRepositoryCustom {
//    @Query("SELECT t FROM Trip t WHERE t.address LIKE CONCAT('%', :address, '%')")
//    Page<Trip> findByAddress(@Param("address") String address, Pageable pageable);
//
//}

