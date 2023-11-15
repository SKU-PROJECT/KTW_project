package com.example.Stay.Service;

import com.example.Stay.Entity.Stay;
import com.example.Stay.Repository.StayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StayService {

    private final StayRepository stayRepository;

    public Stay save(Stay stay) {
        return stayRepository.save(stay);
    }

    public Stay update(Long id, Stay updatedStay) {
        Stay existingStay = findById(id);
        existingStay.setStayCode(updatedStay.getStayCode());
        existingStay.setName(updatedStay.getName());
        existingStay.setCategory(updatedStay.getCategory());
        existingStay.setPrice(updatedStay.getPrice());
        existingStay.setLocationCode(updatedStay.getLocationCode());
        existingStay.setAddress(updatedStay.getAddress());
        existingStay.setService(updatedStay.getService());
        existingStay.setUseGuide(updatedStay.getUseGuide());

        return stayRepository.save(existingStay);
    }

    public void deleteById(Long id) {

        stayRepository.deleteById(id);
    }

    public List<Stay> findAll() {

        return stayRepository.findAll();
    }

    public Stay findById(long id) {
        return stayRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

}
