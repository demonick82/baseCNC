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
import stp.demonick.basecncprog.service.DetailService;
import stp.demonick.basecncprog.service.ProgramService;

@Controller
@SessionAttributes({"detail", "program"})
public class ProgramController {
    private final ProgramService programService;
    private final DetailService detailService;

    public ProgramController(ProgramService programService, DetailService detailService) {
        this.programService = programService;
        this.detailService = detailService;
    }

    @GetMapping({"/programs"})
    public String viewAllProgramsForDetail(@RequestParam("id") long id, Model model) {
        model.addAttribute("detail", detailService.findDetailById(id));
        model.addAttribute("programs", programService.findAllProgramForDetailId(id));
        return "programs_list";
    }

    @GetMapping({"/addProgram"})
    public String add() {
        return "create_pogram";
    }

    @GetMapping({"/update_program"})
    public String update(@RequestParam("id") long id, Model model) {
        model.addAttribute("program", programService.findProgramById(id));
        return "edit_pogramm";
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

    @GetMapping("/deleteProgram")
    public String deleteProgram(@RequestParam("id") long id, Model model) {
        programService.deleteProgram(id);
        Detail detail = (Detail) model.getAttribute("detail");
        if (detail != null) {
            return "redirect:/programs/?id=" + detail.getId();
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/viewPrt")
    public String viewPrtFiles(@RequestParam("id") long id, Model model) {
        String prtFolder = programService.findProgramById(id).getModelPath();
        programService.openDirs(prtFolder);
        Detail detail = (Detail) model.getAttribute("detail");
        if (detail != null) {
            return "redirect:/programs/?id=" + detail.getId();
        } else {
            return "redirect:/";
        }
    }


    @GetMapping("/viewCNC")
    public String viewCNCFiles(@RequestParam("id") long id, Model model) {
        String prtFolder = programService.findProgramById(id).getProgramPath();
        programService.openDirs(prtFolder);
        Detail detail = (Detail) model.getAttribute("detail");
        if (detail != null) {
            return "redirect:/programs/?id=" + detail.getId();
        } else {
            return "redirect:/";
        }
    }
}
