package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.service.DetailService;

@Controller
public class AddProgramControl {

    private final DetailService modelService;

    public AddProgramControl(DetailService modelService) {
        this.modelService = modelService;
    }

    @GetMapping({"/add"})
    public String add() {
        return "add_pogramm";
    }

    @PostMapping("/upload")
    public String getFile(@RequestParam("file") MultipartFile file) {
        modelService.addModel(file);
        return "redirect:/";
    }
}
