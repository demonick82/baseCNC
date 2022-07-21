package stp.demonick.basecncprog.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.model.OperationBlank;
import stp.demonick.basecncprog.model.Program;
import stp.demonick.basecncprog.service.DetailService;
import stp.demonick.basecncprog.service.OperationBlankService;
import stp.demonick.basecncprog.service.ProgramService;

import javax.servlet.http.HttpServletRequest;


@Controller
@SessionAttributes({"detail", "program"})

public class ProgramListControl {
    private final DetailService detailService;
    private final ProgramService programService;
    private final OperationBlankService operationBlankService;

    public ProgramListControl(DetailService detailService, ProgramService programService,
                              OperationBlankService operationBlankService) {
        this.detailService = detailService;
        this.programService = programService;
        this.operationBlankService = operationBlankService;
    }

    @GetMapping({"/programs"})
    public String viewOperations(@RequestParam("id") long id, Model model) {
        model.addAttribute("detail", detailService.findDetailById(id));
        model.addAttribute("programs", programService.findAllProgramForDetailId(id));
        return "programs_list";
    }

    @GetMapping("/operation_blank_view")
    public String viewOperationBlank(@RequestParam("id") long id, Model model) {
        Program program = programService.findProgramById(id);
        model.addAttribute("program", program);
        OperationBlank blank = program.getOperationBlank();
        if (blank != null) {
            String[] comment = blank.getComment().split(System.lineSeparator());
            model.addAttribute("comments", comment);
            return "operation_blank";
        } else {
            return "operation_blank_create";
        }
    }

    @GetMapping("/operationBlankPhoto/{programId}")
    public ResponseEntity<Resource> downloadBlankPhoto(@PathVariable("programId") long id) {
        byte[] img = operationBlankService.downLoadImage(programService.findProgramById(id));
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(img.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(img));
    }


    @PostMapping("/addOperationBlank")
    public String addOperationBlank(@RequestParam("file") MultipartFile file,
                                    Model model, HttpServletRequest req) {
        Program program = (Program) model.getAttribute("program");
        operationBlankService.saveBlank(req.getParameter("comment"), file, program);
        Detail detail = (Detail) model.getAttribute("detail");
        return "redirect:/programs/?id=" + detail.getId();
    }


/*    @GetMapping("/viewPrt")
    public String viewPrtFiles(@RequestParam("id") long id, Model model) {
        String prtFolder = programService.findProgramById(id).getModelPath();
        programService.openFile(prtFolder);
        Detail detail = (Detail) model.getAttribute("detail");
        return "redirect:/programs/?id=" + detail.getId();
    }

    @GetMapping("/viewCNC")
    public String viewCNCFiles(@RequestParam("id") long id, Model model) {
        String prtFolder = programService.findProgramById(id).getProgramPath();
        programService.openFile(prtFolder);
        Detail detail = (Detail) model.getAttribute("detail");
        return "redirect:/programs/?id=" + detail.getId();
    }*/

    @GetMapping("/deleteProgram")
    public String deleteProgram(@RequestParam("id") long id, Model model) {
        programService.deleteProgram(id);
        Detail detail = (Detail) model.getAttribute("detail");
        return "redirect:/programs/?id=" + detail.getId();
    }
}
