package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import stp.demonick.basecncprog.service.DetailService;

@Controller
public class EditDetailControl {
    private final DetailService detailService;

    public EditDetailControl(DetailService detailService) {
        this.detailService = detailService;
    }


    @GetMapping({"/editDetail"})
    public String editDetail(@RequestParam("id") long id, Model model) {
        model.addAttribute("detail", detailService.findDetailById(id));
        return "edit_detail";
    }
}
