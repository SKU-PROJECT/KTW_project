package com.example.Stay.Controller;

import com.example.Stay.Entity.Stay;
import com.example.Stay.Service.StayService;
import com.example.Stay.dto.StayDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class StayController {
    private final StayService stayService;

    //숙소 전체 리스트
    @GetMapping("/stays")
    public String getStays(Model model) {
        List<StayDto> stays = stayService.findAll()
                .stream()
                .map(StayDto::new)
                .toList();
        model.addAttribute("stays", stays);

        return "stay/stayPage";
    }
    //카테고리별로 숙소 조회
    @GetMapping("/stays/category")
    public String getStaysByCategory(@RequestParam("category") String category, Model model) {
        List<Stay> staysByCategory = stayService.findByCategory(category);
        model.addAttribute("stays", staysByCategory);
        return "stay/stayPage";
    }

    //숙소 상세조회
    @GetMapping("/stays/{id}")
    public String getStay(@PathVariable Long id, Model model) {
        Stay stay = stayService.findById(id);
        model.addAttribute("stay", new StayDto(stay));

        return "stay/stay";
    }
    //숙소 등록
    @GetMapping("/stays/create")
    public String showCreateStayForm(Model model) {
        model.addAttribute("stay", new Stay());
        return "stay/createStay";
    }

    @PostMapping("/stays/create")
    public String createStay(@ModelAttribute Stay stay) {
        stayService.save(stay);
        return "redirect:/stays";
    }

    //숙소 수정
    @GetMapping("/stays/edit/{id}")
    public String showEditStayForm(@PathVariable Long id, Model model) {
        Stay stay = stayService.findById(id);
        model.addAttribute("stay", stay);
        return "stay/editStay";
    }

    @PostMapping("/stays/edit/{id}")
    public String editStay(@PathVariable Long id, @ModelAttribute Stay updatedStay) {
        stayService.update(id, updatedStay);
        return "redirect:/stays";
    }

    //숙소 삭제
    @PostMapping("/stays/delete/{id}")
    public String deleteStay(@PathVariable Long id) {
        stayService.deleteById(id);
        return "redirect:/stays";

    }
    @GetMapping("/stays/delete/{id}")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        Stay stay = stayService.findById(id);
        model.addAttribute("stay", stay);
        return "stay/stayPage";
    }

//    @Autowired
//    public StayController(StayService stayService) {
//        this.stayService = stayService;
//    }
//
//    @GetMapping("/stays/list")
//    public String stayList(@RequestParam(defaultValue = "0") int page, Model model) {
//        Page<StayDto> dtoPage = stayService.stayList(page);
//        model.addAttribute("stayList", dtoPage);
//        return "stay/stayPage";
//    }
}
