package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage() {
        // 여행지 소개를 클릭하면 메인 페이지로 이동하는 뷰 이름을 반환합니다.
        return "mainPage";
    }

}