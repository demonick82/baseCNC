package stp.demonick.basecncprog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import stp.demonick.basecncprog.service.DetailService;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class IndexController {

    private final DetailService detailService;


    public IndexController(DetailService modelService) {
        this.detailService = modelService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("details", detailService.findAllDetails());
        model.addAttribute("username", auth.getName());
        model.addAttribute("increment", new AtomicInteger(0));
        return "index";
    }
}
