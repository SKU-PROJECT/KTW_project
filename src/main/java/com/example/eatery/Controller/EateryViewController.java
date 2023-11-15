package com.example.eatery.Controller;

import com.example.eatery.Dto.EateryListViewResponse;
import com.example.eatery.Dto.EateryViewResponse;
import com.example.eatery.Entity.Eatery;
import com.example.eatery.Service.EateryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;

@RequiredArgsConstructor
@Controller

public class EateryViewController {

    private final EateryService eateryService;

    // 전체 음식점 전체 리스트 뷰 리턴
    @GetMapping("/eaterys")
    public String getEaterys(Model model){
        List<EateryListViewResponse> eaterys = eateryService.findAll()
                .stream()
                .map(EateryListViewResponse::new)
                .toList();
        model.addAttribute("eaterys", eaterys);

        return "eatery/eateryList";
    }

    // 음식점 게시물 리턴 (상세보기)
    @GetMapping("/eaterys/{id}")
    public String getEatery(@PathVariable Long id, Model model) {
        Eatery eatery = eateryService.findById(id);
        model.addAttribute("eatery", new EateryViewResponse(eatery));

        return "eatery/eateryDetail";
    }

    // 음식점 등록, 수정
    @GetMapping("/newEatery")
    public String newEatery(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("eatery", new EateryViewResponse());
        } else {
            Eatery eatery = eateryService.findById(id);
            model.addAttribute("eatery", new EateryViewResponse(eatery));
        }

        return "eatery/newEatery";
    }
}