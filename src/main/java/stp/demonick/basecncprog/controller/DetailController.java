package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.service.DetailService;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
@SessionAttributes({"detail", "program"})
public class DetailController {

    private final DetailService detailService;

    public DetailController(DetailService detailService) {
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

    @GetMapping({"/editDetail"})
    public String editDetail(@RequestParam("id") long id, Model model) {
        model.addAttribute("detail", detailService.findDetailById(id));
        return "edit_detail";
    }

    @GetMapping({"/findByNumber"})
    public String findDetailByNumber(@RequestParam("number") String number, Model model) {
        model.addAttribute("details", detailService.findDetailByNumber(number));
        model.addAttribute("increment", new AtomicInteger(0));
        return "index";
    }

    @GetMapping("/deleteDetail")
    public String deleteDetail(@RequestParam("id") long id) {
        detailService.deleteDetail(id);
        return "redirect:/";
    }
}
