package com.example.Stay.Controller;

import com.example.Stay.Entity.Stay;
import com.example.Stay.Service.StayService;
import com.example.Stay.dto.StayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class StayController {
    private final StayService stayService;

    @GetMapping("/stays")
    public String getStays(Model model) {
        List<StayResponse> stays = stayService.findAll()
                .stream()
                .map(StayResponse::new)
                .toList();
        model.addAttribute("stays", stays);

        return "stay/stayPage";
    }

    @GetMapping("/stays/{id}")
    public String getStay(@PathVariable Long id, Model model) {
        Stay stay = stayService.findById(id);
        model.addAttribute("stay", new StayResponse(stay));

        return "stay/stay";
    }
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


    @GetMapping("/stays/delete/{id}")
    public String showDeleteConfirmation(@PathVariable Long id, Model model) {
        Stay stay = stayService.findById(id);
        model.addAttribute("stay", stay);
        return "stay/stayPage";
    }

    @PostMapping("/stays/delete/{id}")
    public String deleteStay(@PathVariable Long id) {
        stayService.deleteById(id);
        return "redirect:/stays";
    }
}
