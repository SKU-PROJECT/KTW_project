package com.example.Trip.Repository;


import com.example.Trip.Dto.MainItemDto;
import com.example.Trip.Dto.QMainItemDto;
import com.example.Trip.Dto.TripSearchDto;
import com.example.Trip.Entity.QTrip;
import com.example.Trip.Entity.QTripImg;
import com.example.Trip.Entity.Trip;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.util.List;

public class TripRepositoryCustomImpl implements TripRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public TripRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    //여행지명, 여행지역 검색 쿼리
    private BooleanExpression searchByLike(String searchBy, String searchQuery) { //여행지명/ 주소로 검색
        if(StringUtils.equals("name", searchBy)) {
            return QTrip.trip.name.like("%" + searchQuery + "%");

        }else if(StringUtils.equals("address", searchBy)) {
            return QTrip.trip.address.like("%" + searchQuery+ "%");
        }
        return null;
    }

    private BooleanExpression nameLike(String searchQuery){
        return StringUtils.isEmpty(searchQuery) ? null : QTrip.trip.name.like("%" + searchQuery + "%");
    }

//    //조건 쿼리로 페이징된 페이지
//    @Override
//    public Page<Trip> getMainTripPage(TripSearchDto tripSearchDto, Pageable pageable) {
//        QueryResults<Trip> results = queryFactory
//                .selectFrom(QTrip.trip)
//                .where(searchByLike(tripSearchDto.getSearchBy(), tripSearchDto.getSearchQuery()))
//                .orderBy(QTrip.trip.id.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetchResults();
//
//        List<Trip> content = results.getResults();
//        long total = results.getTotal();
//        return new PageImpl<>(content, pageable, total);
//    }

    @Override
    public Page<MainItemDto> getTripPage (TripSearchDto tripSearchDto, Pageable pageable) {
        QTrip trip = QTrip.trip;
        QTripImg tripImg = QTripImg.tripImg;

        QueryResults<MainItemDto> results = queryFactory
        .select(
                new QMainItemDto(
                        trip.id,
                        trip.name,
                        trip.address,
                        tripImg.imgUrl)
        )
                .from(tripImg)
                .join(tripImg.trip, trip)
                .where(tripImg.repimgYn.eq("Y"))
                .where(nameLike(tripSearchDto.getSearchQuery()))
                .orderBy(trip.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainItemDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content,pageable, total);
    }
}
