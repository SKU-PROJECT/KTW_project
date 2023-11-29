package com.example.Stay.Repository;

import com.example.Stay.Entity.Stay;
import com.example.Stay.dto.StaySearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StayRepositoryCustom {
    Page<Stay> getAdminStayPage(StaySearchDto staySearchDto, Pageable pageable);
}
