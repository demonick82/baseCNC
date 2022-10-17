package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.service.DetailService;


@Controller
public class AddDetailControl {
    private final DetailService detailService;

    public AddDetailControl(DetailService detailService) {
        this.detailService = detailService;
    }


    @GetMapping({"/newDetail"})
    public String addDetail() {
        return "add_detail";
    }

    @PostMapping("/addDetail")
    public String save(@ModelAttribute Detail detail,
                       Model model) {
        try {
            detailService.save(detail);
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Деталь уже существует");
        }
        return "redirect:/";
    }
}
