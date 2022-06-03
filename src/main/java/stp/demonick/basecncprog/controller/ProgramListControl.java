package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import stp.demonick.basecncprog.service.DetailService;
import stp.demonick.basecncprog.service.ProgramService;

@Controller
@SessionAttributes(value = "detailName")
public class ProgramListControl {
    private final DetailService detailService;
    private final ProgramService programService;

    public ProgramListControl(DetailService detailService, ProgramService programService) {
        this.detailService = detailService;
        this.programService = programService;
    }

    @GetMapping({"/programs"})
    public String viewOperations(@RequestParam("id") int id, Model model) {
        model.addAttribute("detailName", detailService.findDetailById(id).getPartName());
        model.addAttribute("programs", programService.findAllProgramForDetailId(id));
        return "programs_list";
    }
}
