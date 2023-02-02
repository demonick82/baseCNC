package stp.demonick.basecncprog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.service.ProgramService;

@Controller
@SessionAttributes({"detail", "program"})
public class ProgramController {
    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping({"/addProgram"})
    public String add() {
        return "add_pogramm";
    }

    @GetMapping({"/update_program"})
    public String update(@RequestParam("id") long id, Model model) {
        model.addAttribute("program", programService.findProgramById(id));
        return "update_pogramm";
    }

    @PostMapping("/upload")
    public String createProgram(@RequestParam("file") MultipartFile file, Model model) {
        Detail detail = (Detail) model.getAttribute("detail");
        long id = detail.getId();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        programService.createProgram(file, id, auth.getName());
        return "redirect:/programs/?id=" + id;
    }

    @PostMapping("/update_program")
    public String updateProgram(@RequestParam("id") long id, @RequestParam("file") MultipartFile file) {
        programService.updateProgram(id, file);
        return "redirect:/index";
    }
}
