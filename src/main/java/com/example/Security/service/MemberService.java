package com.example.Security.service;

import com.example.Security.entity.Member;
import com.example.Security.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
//    private final LikeRepository likeRepository;
//    private final CommentRepository commentRepository;
  //  private final BCryptPasswordEncoder encoder;

    //회원 등록
    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    //유효성 검사
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByMemId(member.getMemId());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        //이미 가입된 회원인 경우 예외처리
        }
    }

    //로그인 처리
    @Override
    public UserDetails loadUserByUsername(String memId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemId(memId);

        if(member == null) {
            throw new UsernameNotFoundException(memId);
        }

        return User.builder()                           // UserDetail을 구현하고 있는 User 객체를 반환해줌. User 객체를 생성하기 위해 생성자로 회원의 이메일, 비밀번호, role을 파라미터로 넘겨줌.
                .username(member.getMemId())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

//    public Member myInfo(String memId) {
//        return memberRepository.findByMemId(memId);
//    }
//    public Page<Member> findAllByMemId(String keyword, PageRequest pageRequest) {
//        return memberRepository.findAllByMemIdContains(keyword, pageRequest);
//    }

//

//    public Member findByMember_id(long member_id) {
//        Optional<Member> optionalMember = Optional.ofNullable(memberRepository.findByMember_id(member_id));
//
//        if(optionalMember.isEmpty()){
//            throw new RuntimeException("해당하는 회원이 존재하지 않습니다.");
//        }
//
//        return optionalMember.get();
//    }

}