package com.example.Stay.Repository;


import com.example.Stay.Entity.Stay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;


public interface StayRepository extends JpaRepository<Stay, Long>, QuerydslPredicateExecutor<Stay>, StayRepositoryCustom {
    @Query("select s " +
            "from Stay s " +
            "where s.category like %:category% " +
            "and s.address like %:address% " +
            "order by s.price desc")
    Page<Stay> findByCategory(@Param("category") String category, @Param("address") String address, Pageable pageable);


//    @Query(value = "SELECT * FROM stay s " +
//            "WHERE s.category LIKE %:category% " +
//            "AND s.address LIKE %:address% " +
//            "ORDER BY s.price DESC " +
//            "LIMIT :offset, :limit", nativeQuery = true)
//    List<Stay> findByCategoryNative(@Param("category") String category, @Param("address") String address, @Param("offset") long offset, @Param("limit") int limit);

}
