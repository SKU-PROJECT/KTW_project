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
        existingStay.setDetail(updatedStay.getDetail());
        existingStay.setName(updatedStay.getName());
        existingStay.setCategory(updatedStay.getCategory());
        existingStay.setPrice(updatedStay.getPrice());
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

    public List<Stay> findByCategory(String category) {
        return stayRepository.findByCategory(category);
    }


////    페이징 & 정렬
//    public Page<StayDto> stayList(int page) {
//        PageRequest pageRequest = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "category"));
//        Page<Stay> pageMap = stayRepository.findAll(pageRequest);
//        Page<StayDto> toDtoMap = pageMap.map(stay -> new StayDto(stay));
//        return toDtoMap;
//    }

//    public Page<StayDto> stayList(int page) {
//        PageRequest pageRequest = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "category"));
//        Page<Stay> pageMap = stayRepository.findAll(pageRequest);
//        Page<StayDto> toDtoMap = pageMap.map(stay -> new StayDto(stay));
//        return toDtoMap;
//    }

}
