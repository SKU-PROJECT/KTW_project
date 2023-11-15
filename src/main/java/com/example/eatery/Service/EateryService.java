package com.example.eatery.Service;

import com.example.eatery.Dto.AddEateryRequest;
import com.example.eatery.Dto.UpdateEateryRequest;
import com.example.eatery.Entity.Eatery;
import com.example.eatery.Repository.EateryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EateryService {

    private final EateryRepository eateryRepository;

    //추가
    public Eatery save(AddEateryRequest request) {
        return eateryRepository.save(request.toEntity());
    }

    // 전체 조회
    public List<Eatery> findAll() {
        return eateryRepository.findAll();
    }

    // 단건 조회
    public Eatery findById(long id) {
        return eateryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }
    //
    //삭제
    public void delete(long id) {
        eateryRepository.deleteById(id);
    }

    //수정
    @Transactional
    public Eatery update(long id, UpdateEateryRequest request) {
        Eatery eatery = eateryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        eatery.update(request.getCode(), request.getCategory(), request.getName(), request.getLocation(),
                request.getAddress(), request.getDetail());

        return eatery;
    }
}
