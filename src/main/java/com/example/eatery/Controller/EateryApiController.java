package com.example.eatery.Controller;

import com.example.eatery.Dto.AddEateryRequest;
import com.example.eatery.Dto.EateryResponse;
import com.example.eatery.Dto.UpdateEateryRequest;
import com.example.eatery.Entity.Eatery;
import com.example.eatery.Service.EateryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EateryApiController {

    private final EateryService eateryService;

    //등록
    @PostMapping("/api/eaterys")
    public ResponseEntity<Eatery> addEatery(@RequestBody AddEateryRequest request){
        Eatery savedEatery = eateryService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedEatery);
    }

    //전체조회
    @GetMapping("/api/eaterys")
    public ResponseEntity<List<EateryResponse>> findAllEaterys(){
        List<EateryResponse> eaterys = eateryService.findAll()
                .stream()
                .map(EateryResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(eaterys);
    }

    //상세조회
    @GetMapping("/api/eaterys/{id}")
    public ResponseEntity<EateryResponse> findEatery(@PathVariable long id){
        Eatery eatery = eateryService.findById(id);

        return ResponseEntity.ok()
                .body(new EateryResponse(eatery));
    }

    //삭제
    @DeleteMapping("/api/eaterys/{id}")
    public ResponseEntity<Void> deleteEatery(@PathVariable long id){
        eateryService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    //수정
    @PutMapping("/api/eaterys/{id}")
    public ResponseEntity<Eatery> updateEatery(@PathVariable long id,
                                               @RequestBody UpdateEateryRequest request){
        Eatery updatedEatery = eateryService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedEatery);
    }

}
