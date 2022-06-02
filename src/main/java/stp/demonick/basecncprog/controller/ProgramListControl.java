package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import stp.demonick.basecncprog.service.ProgramService;

@Controller
public class ProgramListControl {
    private final ProgramService programService;

    public ProgramListControl(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping({"/programs"})
    public String viewOperations(@RequestParam("id") int id, Model model) {
        model.addAttribute("programs", programService.findAllProgramForDetailId(id));
        return "programs_list";
    }
}
