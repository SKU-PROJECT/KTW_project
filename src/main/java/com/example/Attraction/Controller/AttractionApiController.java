package com.example.Attraction.Controller;

import com.example.Attraction.Dto.AddAttractionRequest;
import com.example.Attraction.Dto.AttractionResponse;
import com.example.Attraction.Dto.UpdateAttractionRequest;
import com.example.Attraction.Entity.Attraction;
import com.example.Attraction.Service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class AttractionApiController {

    private final AttractionService attractionService;

    @PostMapping("/api/attractions")
    public ResponseEntity<Attraction> addAttraction(@RequestBody AddAttractionRequest request){
        Attraction savedAttraction = attractionService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedAttraction);
    }

//    @GetMapping("/api/attactions")
//    public ResponseEntity<List<AttractionResponse>> findAllAttractions(){
//        List<AttractionResponse> attractions = attractionService.findAll()
//                .stream()
//                .map(AttractionResponse::new)
//                .toList();
//
//        return ResponseEntity.ok()
//                .body(attractions);
//    }

    //상세조회
    @GetMapping("/api/attractions/{id}")
    public ResponseEntity<AttractionResponse> findAttraction(@PathVariable long id){
        Attraction attraction = attractionService.findById(id);

        return ResponseEntity.ok()
                .body(new AttractionResponse(attraction));
    }

    //삭제
    @DeleteMapping("/api/attractions/{id}")
    public ResponseEntity<Void> deleteAttraction(@PathVariable long id){
        attractionService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    //수정
    @PutMapping("/api/attractions/{id}")
    public ResponseEntity<Attraction> updateAttraction(@PathVariable long id,
                                               @RequestBody UpdateAttractionRequest request){
        Attraction updatedAttraction = attractionService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedAttraction);
    }

}
