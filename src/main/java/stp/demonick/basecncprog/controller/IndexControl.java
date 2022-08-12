package stp.demonick.basecncprog.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.model.OperationBlank;
import stp.demonick.basecncprog.model.Program;
import stp.demonick.basecncprog.service.DetailService;

@Controller
public class IndexControl {

    private final DetailService detailService;


    public IndexControl(DetailService modelService) {
        this.detailService = modelService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("details", detailService.findAllDetails());
        model.addAttribute("username", auth.getName());
        return "index";
    }

    @GetMapping("/deleteDetail")
    public String deleteDetail(@RequestParam("id") long id) {
        detailService.deleteDetail(id);
        return "redirect:/";
    }
}
