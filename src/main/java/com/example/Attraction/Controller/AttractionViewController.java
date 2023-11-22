package com.example.Attraction.Controller;

import com.example.Attraction.Dto.AttractionListViewResponse;
import com.example.Attraction.Dto.AttractionResponse;
import com.example.Attraction.Dto.AttractionViewResponse;
import com.example.Attraction.Entity.Attraction;
import com.example.Attraction.Service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AttractionViewController {

    private final AttractionService attractionService;

    // 페이징
    @GetMapping("/attractions")
    public String getAttractions(Model model, @RequestParam(value="page", defaultValue = "0") int page){
        Page<Attraction> attractionsPaging = this.attractionService.findAll(page);
        model.addAttribute("attractionPaging", attractionsPaging);
        return "attraction/attractionList";
    }

    @GetMapping("/attractions/{id}")
    public String getAttraction(@PathVariable Long id, Model model) {
        Attraction attraction = attractionService.findById(id);
        model.addAttribute("attraction", new AttractionViewResponse(attraction));

        return "attraction/attractionDetail";
    }

    @GetMapping("/newAttraction")
    public String newAttraction(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("attraction", new AttractionViewResponse());
        } else {
            Attraction attraction = attractionService.findById(id);
            model.addAttribute("attraction", new AttractionViewResponse(attraction));
        }

        return "attraction/newAttraction";
    }

}