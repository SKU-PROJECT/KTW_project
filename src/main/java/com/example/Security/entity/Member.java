package com.example.Security.entity;


import com.example.Security.constant.Role;
import com.example.Security.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;


    private String memId;   // 로그인 아이디
    private String name;    //이름
    @Column(unique = true)
    private String email;   //이메일
    private String password; //비밀번호
    private String address;  //주소

    @Enumerated(EnumType.STRING)    //사용자 권한
    private Role role;

    private LocalDateTime createdAt; //가입시간
//    private Integer receivedLikeCnt; //사용자가 받은 좋아요 개수
//
//    @OneToMany(mappedBy = "member", orphanRemoval = true)
//    private List<Board> boards; // 작성글
//
//    @OneToMany(mappedBy = "member", orphanRemoval = true)
//    private List<Like> likes; //유저가 누른 좋아요
//
//    @OneToMany(mappedBy="member", orphanRemoval = true)
//    private List<Comment> comments; //댓글


//    public void edit(String newPassword, String memId) {
//        this.password = newPassword;
//        this.memId = memId;
//    }

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder, Role role){
        Member member = new Member();
        member.setMemId(memberFormDto.getMemId());
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);  // 여기서 전달받은 role을 사용하도록 변경
        return member;
    }

}