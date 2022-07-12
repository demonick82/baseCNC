package stp.demonick.basecncprog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.service.ProgramService;

@Controller
@SessionAttributes({"id"})
public class AddProgramControl {

    private final ProgramService programService;

    public AddProgramControl(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping({"/addProgram"})
    public String add() {
        return "add_pogramm";
    }

    @PostMapping("/upload")
    public String getFile(@RequestParam("file") MultipartFile file, Model model) {
        int id = (int) model.getAttribute("id");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        programService.addProgram(file, id,auth.getName());
        return "redirect:/programs/?id="+id;
    }
}
