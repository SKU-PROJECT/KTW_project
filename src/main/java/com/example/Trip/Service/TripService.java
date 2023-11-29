package com.example.Trip.Service;

import com.example.Trip.Dto.MainItemDto;
import com.example.Trip.Dto.TripFormDto;
import com.example.Trip.Dto.TripImgDto;
import com.example.Trip.Dto.TripSearchDto;
import com.example.Trip.Entity.Trip;
import com.example.Trip.Entity.TripImg;
import com.example.Trip.Repository.TripImgRepository;
import com.example.Trip.Repository.TripRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class TripService {

    private final TripRepository tripRepository;
    private final TripImgService tripImgService;
    private final TripImgRepository tripImgRepository;


    //여행지 등록
    public Long saveTrip(TripFormDto tripFormDto, List<MultipartFile> tripImgFileList) throws Exception {
        Trip trip = tripFormDto.createTrip();
        tripRepository.save(trip);

        //이미지 등록
        for(int i=0;i<tripImgFileList.size();i++){
            TripImg tripImg = new TripImg();
            tripImg.setTrip(trip);

            if(i == 0)
                tripImg.setRepimgYn("Y");
            else
                tripImg.setRepimgYn("N");

            tripImgService.saveTripImg(tripImg, tripImgFileList.get(i));
        }
        return trip.getId();
    }


    //여행지 전체 리스트 출력
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    public Trip findById(long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }


//여행지 상세 조회
    @Transactional
    public TripFormDto getTripDtl(Long id){
        List<TripImg> tripImgList = tripImgRepository.findByTrip_IdOrderByIdAsc(id);
        List<TripImgDto> tripImgDtoList = new ArrayList<>();

        for (TripImg tripImg : tripImgList) {
            TripImgDto tripImgDto = TripImgDto.of(tripImg);
            tripImgDtoList.add(tripImgDto);
        }

        Trip trip = tripRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        TripFormDto tripFormDto = TripFormDto.of(trip);
        tripFormDto.setTripImgDtoList(tripImgDtoList);

        return tripFormDto;
    }


    // main/trips/
    @Transactional
    public Page<Trip> getMainTripPage(TripSearchDto tripSearchDto, Pageable pageable) {
        return tripRepository.getMainTripPage(tripSearchDto, pageable) ;
    }

    @Transactional
    public Page<MainItemDto> getTripPage(TripSearchDto tripSearchDto, Pageable pageable) {
        return tripRepository.getTripPage(tripSearchDto, pageable);
    }


    //여행지 수정
    public Long updateTrip(TripFormDto tripFormDto, List<MultipartFile> tripImgFileList) throws  Exception {
        Trip trip = tripRepository.findById(tripFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        trip.updateTrip(tripFormDto);
        List<Long> tripImgIds = tripFormDto.getTripImgIds();

        //이미지 등록
        for(int i=0; i<tripImgFileList.size(); i++) {
            tripImgService.updateTripImg(tripImgIds.get(i), tripImgFileList.get(i));
        }
        return trip.getId();
    }
}
