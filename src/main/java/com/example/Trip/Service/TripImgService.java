package com.example.Trip.Service;

import com.example.Trip.Entity.TripImg;
import com.example.Trip.Repository.TripImgRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class TripImgService {

    //프로젝트 상대경로 설정
    private final static String uploadPath = System.getProperty("user.dir").concat("\\src\\main\\resources\\static\\trip\\");

    private final TripImgRepository tripImgRepository;

    private final FileService fileService;


    //이미지 저장
    public void saveTripImg(TripImg tripImg, MultipartFile tripImgFile) throws Exception{
        String oriImgName = tripImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(uploadPath, oriImgName,
                    tripImgFile.getBytes());
            imgUrl = "/trip/" + imgName;
        }

        //이미지 정보 저장
        tripImg.updateTripImg(oriImgName, imgName, imgUrl);
        tripImgRepository.save(tripImg);
    }



    //이미지 수정
    public void updateTripImg(Long tripImgId, MultipartFile tripImgFile) throws Exception{
        if(!tripImgFile.isEmpty()){
            TripImg savedTripImg = tripImgRepository.findById(tripImgId)
                    .orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일 삭제
            if(!StringUtils.isEmpty(savedTripImg.getImgName())) {
                fileService.deleteFile(uploadPath+"/"+ savedTripImg.getImgName());
            }

            String oriImgName = tripImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(uploadPath, oriImgName, tripImgFile.getBytes());
            String imgUrl = "/trip/" + imgName;
            savedTripImg.updateTripImg(oriImgName, imgName, imgUrl);
        }
    }

}
