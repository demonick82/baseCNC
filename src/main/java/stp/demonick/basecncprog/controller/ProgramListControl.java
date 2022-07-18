package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.model.OperationBlank;
import stp.demonick.basecncprog.service.DetailService;
import stp.demonick.basecncprog.service.OperationBlankService;
import stp.demonick.basecncprog.service.ProgramService;

import javax.servlet.http.HttpServletRequest;


@Controller
@SessionAttributes({"detail", "id"})

public class ProgramListControl {
    private final DetailService detailService;
    private final ProgramService programService;
    private final OperationBlankService operationBlankService;

    public ProgramListControl(DetailService detailService, ProgramService programService, OperationBlankService operationBlankService) {
        this.detailService = detailService;
        this.programService = programService;
        this.operationBlankService = operationBlankService;
    }

    @GetMapping({"/programs"})
    public String viewOperations(@RequestParam("id") int id, Model model) {
        model.addAttribute("detail", detailService.findDetailById(id));
        model.addAttribute("programs", programService.findAllProgramForDetailId(id));
        model.addAttribute("id", id);
        return "programs_list";
    }

    @GetMapping("/operation_blank_view")
    public String viewOperationBlank(@RequestParam("id") int id, Model model) {
        OperationBlank blank = programService.findProgramById(id).getOperationBlank();
        if (blank != null) {
            model.addAttribute("blank", blank);
            return "operation_blank";
        }
        return "operation_blank_create";
    }

    @PostMapping("/addOperationBlank")
    public String addOperationBlank(@RequestParam("file") MultipartFile file, Model model, HttpServletRequest req) {
        operationBlankService.saveBlank(req.getParameter("name"),file);
        Detail detail = (Detail) model.getAttribute("detail");
        return "redirect:/programs/?id=" + detail.getId();
    }


    @GetMapping("/viewPrt")
    public String viewPrtFiles(@RequestParam("id") int id, Model model) {
        String prtFolder = programService.findProgramById(id).getModelPath();
        programService.openFile(prtFolder);
        Detail detail = (Detail) model.getAttribute("detail");
        return "redirect:/programs/?id=" + detail.getId();
    }

    @GetMapping("/viewCNC")
    public String viewCNCFiles(@RequestParam("id") int id, Model model) {
        String prtFolder = programService.findProgramById(id).getProgramPath();
        programService.openFile(prtFolder);
        Detail detail = (Detail) model.getAttribute("detail");
        return "redirect:/programs/?id=" + detail.getId();
    }

    @GetMapping("/deleteProgram")
    public String deleteProgram(@RequestParam("id") long id, Model model) {
        programService.deleteProgram(id);
        Detail detail = (Detail) model.getAttribute("detail");
        return "redirect:/programs/?id=" + detail.getId();
    }
}
