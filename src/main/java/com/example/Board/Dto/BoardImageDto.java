//package com.example.Board.Dto;
//
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//
//@NoArgsConstructor
//@AllArgsConstructor
//public class BoardImageDto {
//    private String uuid;
//    private String imgName;
//    private String path;
//
//    public String getImageURL() {
//        try{
//            return URLEncoder.encode(path+"/"+uuid+ "_"+imgName, "UTF-8");
//        }catch(UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//    public String getThumbnailURL() {
//        try {
//            return URLEncoder.encode(path+"/"+uuid+ "_"+imgName, "UTF-8");
//        }catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//}
