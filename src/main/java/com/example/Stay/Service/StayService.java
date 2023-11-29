package com.example.Stay.Service;


import com.example.Stay.Entity.Stay;
import com.example.Stay.Repository.StayRepository;
import com.example.Stay.dto.StaySearchDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class StayService {

    private final StayRepository stayRepository;

    public Stay save(Stay stay) {
        return stayRepository.save(stay);
    }

    public Stay update(Long stay_id, Stay updatedStay) {
        Stay existingStay = findById(stay_id);
        existingStay.setDetail(updatedStay.getDetail());
        existingStay.setName(updatedStay.getName());
        existingStay.setCategory(updatedStay.getCategory());
        existingStay.setPrice(updatedStay.getPrice());
        existingStay.setStayday(updatedStay.getStayday());
        existingStay.setStaySellStatus(updatedStay.getStaySellStatus());
        existingStay.setAddress(updatedStay.getAddress());
        existingStay.setService(updatedStay.getService());
        existingStay.setUse_guide(updatedStay.getUse_guide());
        existingStay.setAmenity(updatedStay.getAmenity());
        return stayRepository.save(existingStay);
    }

    public void deleteById(Long stay_id) {
        stayRepository.deleteById(stay_id);
    }

    public List<Stay> findAll() {
        return stayRepository.findAll();
    }

    public Stay findById(long stay_id) {
        return stayRepository.findById(stay_id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + stay_id));
    }

    public Page<Stay> findByCategory(String category, String address, Pageable pageable) {
        return stayRepository.findByCategory(category, address, pageable);
    }

    @Transactional
    public Page<Stay> getAdminStayPage(StaySearchDto staySearchDto, Pageable pageable) {
        return stayRepository.getAdminStayPage(staySearchDto, pageable);
    }

}
