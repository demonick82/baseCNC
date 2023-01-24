package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import stp.demonick.basecncprog.service.DetailService;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class DetailController {
    private final DetailService detailService;

    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping({"/findByNumber"})
    public String findDetailByNumber(@RequestParam("number") String number, Model model) {
        model.addAttribute("details", detailService.findDetailByNumber(number));
        model.addAttribute("increment", new AtomicInteger(0));
        return "index";
    }

}
