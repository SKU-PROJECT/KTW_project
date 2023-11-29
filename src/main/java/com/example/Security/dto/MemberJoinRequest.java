package com.example.Security.dto;

import com.example.Security.entity.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberJoinRequest {
    private String memId; //아이디
    private String password;      //비밀번호
    private String passwordCheck;   //비밀번호체크


    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .memId(memId)
                .password(encodedPassword)
                .createdAt(LocalDateTime.now())
//                .receivedLikeCnt(0)
                .build();
    }
}
