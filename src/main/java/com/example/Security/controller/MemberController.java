package com.example.Security.controller;


import com.example.Security.constant.Role;
import com.example.Security.dto.MemberFormDto;
import com.example.Security.entity.Member;
import com.example.Security.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
//    private final BoardService boardService;

    private final PasswordEncoder passwordEncoder;

    //회원가입
    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/Signup";
    }

    @PostMapping(value = "/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){

        //validation
        if(bindingResult.hasErrors()){
            return "member/Signup";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder, Role.USER);
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/LoginForm";
        }

        return "redirect:/";
    }

    //로그인폼
    @GetMapping(value = "/login")
    public String loginMember(){

        return "member/LoginForm";
    }

    //로그인 에러시
    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "member/LoginForm";
    }

    //마이페이지 접근
//    @GetMapping("/myPage/{category}")
//    public String myPage(@PathVariable String category, Authentication auth, Model model) {
////        model.addAttribute("boards", boardService.findMyBoard(category, auth.getName()));
//        model.addAttribute("category", category);
//        model.addAttribute("member", memberService.myInfo(auth.getName()));
//        return "member/myPage";
//    }

    //회원수정

    //회원삭제


    //관리자페이지
//    @GetMapping("/admin")
//    public String adminPage(@RequestParam(required = false, defaultValue = "1") int page,
//                            @RequestParam(required = false, defaultValue = "") String keyword,
//                            Model model) {
//        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by("id").descending());
//        Page<Member> members = memberService.findAllByMemId(keyword, pageRequest);
//
//        model.addAttribute("members", members);
//        model.addAttribute("keyword", keyword);
//
//        return "adminPage";
//    }



}