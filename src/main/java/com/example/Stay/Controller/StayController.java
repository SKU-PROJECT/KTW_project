package com.example.Stay.Controller;

import com.example.Stay.Entity.Stay;
import com.example.Stay.Service.StayService;
import com.example.Stay.dto.StayDto;
import com.example.Stay.dto.StaySearchDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class StayController {
    private final StayService stayService;
    @Autowired
    private HttpServletRequest request;

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
    public String getStaysByCategory(@RequestParam(value = "category", required = false) String category,
                                     @RequestParam(value = "address", required = false) String address,
                                     @PageableDefault(page=0, size=10) Pageable pageable,
                                     Model model) {
        Page<Stay> staysByCategory = stayService.findByCategory(category, address, pageable);
        model.addAttribute("stays", staysByCategory);
        return "stay/stayPage";
    }

//    @GetMapping("/stays/category")
//    public String getStaysByCategory(@RequestParam(value = "category", required = false) String category,
//                                     @RequestParam(value = "address", required = false) String address,
//                                     @RequestParam(value = "offset", required = false, defaultValue = "0") long offset,
//                                     @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
//                                     Model model) {
//        List<Stay> staysByCategory = stayService.findByCategory(category, address, offset, limit);
//        model.addAttribute("stays", staysByCategory);
//        return "stay/stayPage";
//    }

    //숙소 상세조회
    @GetMapping("/stays/{stay_id}")
    public String getStay(@PathVariable Long stay_id, Model model) {
        Stay stay = stayService.findById(stay_id);
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
    @GetMapping("/stays/edit/{stay_id}")
    public String showEditStayForm(@PathVariable Long stay_id, Model model) {
        Stay stay = stayService.findById(stay_id);
        model.addAttribute("stay", stay);
        return "stay/editStay";
    }

    @PostMapping("/stays/edit/{stay_id}")
    public String editStay(@PathVariable Long stay_id, @ModelAttribute Stay updatedStay) {
        stayService.update(stay_id, updatedStay);
        return "redirect:/stays";
    }

    //숙소 삭제
    @PostMapping("/stays/delete/{stay_id}")
    public String deleteStay(@PathVariable Long stay_id) {
        stayService.deleteById(stay_id);
        return "redirect:/stays";

    }
    @GetMapping("/stays/delete/{stay_id}")
    public String showDeleteConfirmation(@PathVariable Long stay_id, Model model) {
        Stay stay = stayService.findById(stay_id);
        model.addAttribute("stay", stay);
        return "stay/stayPage";
    }


    //관리자-숙소관리
    @GetMapping(value={"/admin/stays", "/admin/stays/{page}"}) // 숙소관리화면 페이지 진입시 페이지 번호가 있는경우와 없는경우
    public String stayManage(StaySearchDto staySearchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,10);
        Page<Stay> stays =
                stayService.getAdminStayPage(staySearchDto, pageable);
                    model.addAttribute("stays", stays);
                    model.addAttribute("staySearchDto", staySearchDto);
                    model.addAttribute("maxPage", 5);
                    return "stay/stayMng";

    }



}
