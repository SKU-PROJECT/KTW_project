package com.example.Security.dto;

import com.example.Security.entity.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDto {
    private String memId;
    private String nowPassword;   //현재비밀번호
    private String newPassword;  //새로운비밀번호
    private String newPasswordCheck;        //새로운비밀번호체크

    public static MemberDto of(Member member) {
        return MemberDto.builder()
                .memId(member.getMemId())
                .build();
    }
}
