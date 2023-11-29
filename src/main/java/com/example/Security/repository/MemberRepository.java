package com.example.Security.repository;


import com.example.Security.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemId(String memId); //유저 아이디로 회원 찾기

//Admin이 회원아이디를 검색할 때
    Page<Member> findAllByMemIdContains(String memId, PageRequest pageRequest);

    // 회원이 존재하는지
    Boolean existsByMemId(String memId);

}