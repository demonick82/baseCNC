package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import stp.demonick.basecncprog.service.DetailService;

@Controller
public class IndexControl {

    private final DetailService modelService;

    public IndexControl(DetailService modelService) {
        this.modelService = modelService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("details", modelService.findAllDetails());
        return "index";
    }

    @GetMapping("/deleteDetail")
    public String deleteDetail(@RequestParam("id") long id) {
        modelService.deleteDetail(id);
        return "redirect:/";
    }
}
