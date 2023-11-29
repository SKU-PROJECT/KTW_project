package com.example.Stay.Repository;

import com.example.Stay.Constant.StaySellStatus;
import com.example.Stay.Entity.QStay;
import com.example.Stay.Entity.Stay;
import com.example.Stay.dto.StaySearchDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public class StayRepositoryCustomImpl implements StayRepositoryCustom {

    private JPAQueryFactory queryFactory;  //동적으로 쿼리를 생성사기 위해 queryFactory 생성

    public StayRepositoryCustomImpl(EntityManager em) {
            this.queryFactory = new JPAQueryFactory(em);
    }
    private BooleanExpression searchSellStatusEq(StaySellStatus searchSellStatus) {
        return searchSellStatus == null ? null : QStay.stay.staySellStatus.eq(searchSellStatus);
    }//상품의 판매상태조건이 null일 경우에는 null을 리턴

    private BooleanExpression regDtsAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();

        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null;
        } else if (StringUtils.equals("1d", searchDateType)) {
            dateTime = dateTime.minusDays(1);
        } else if (StringUtils.equals("1w", searchDateType)) {
            dateTime = dateTime.minusWeeks(1);
        } else if (StringUtils.equals("1m", searchDateType)) {
            dateTime = dateTime.minusMonths(1);
        } else if (StringUtils.equals("6m", searchDateType)) {
            dateTime = dateTime.minusMonths(6);
        }
        return QStay.stay.regTime.after(dateTime);
    }
    private BooleanExpression searchByLike(String searchBy, String searchQuery) { //숙소명에 검색어를 포함하고 있는 상품을 조회 & 반환
        if(StringUtils.equals("name",searchBy)) {
            return QStay.stay.name.like("%" + searchQuery + "%");
        }
//        else if(StringUtils.equals("createdBy", searchBy)) {
//            return QStay.stay.createdBY.like("%" + searchQuery+"%");
//        }
        return null;
    }

    @Override
    public Page<Stay> getAdminStayPage(StaySearchDto staySearchDto, Pageable pageable) {
        QueryResults<Stay> results = queryFactory
                .selectFrom(QStay.stay)
                .where(regDtsAfter(staySearchDto.getSearchDateType()),
                        searchSellStatusEq(staySearchDto.getSearchSellStatus()),
                        searchByLike(staySearchDto.getSearchBy(),
                                staySearchDto.getSearchQuery()))
                .orderBy(QStay.stay.stay_id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Stay> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);

    }

}
