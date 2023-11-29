package com.example.Trip.Service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
@Log
public class FileService {

    // 이미지 업로드
    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception{
//        UUID uuid = UUID.randomUUID();
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); //확장자
        String savedFileName =  originalFileName;  //저장 파일명
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();
        return savedFileName;
    }

//    // 이미지 URL 안전한 형태로 변환
//    private String encodeFileName(String fileName) throws UnsupportedEncodingException {
//        return URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
//    }

    //이미지 삭제
    public void deleteFile(String filePath) throws Exception{
        File deleteFile = new File(filePath);
        if(deleteFile.exists()) {
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }

//    // 이미지 URL 설정
//    public String getImageUrl(String savedFileName, String originalFileName) throws UnsupportedEncodingException {
//        String encodedFileName = encodeFileName(originalFileName);
//        return "/images/trip/" + savedFileName + "?filename=" + encodedFileName;
//    }

}