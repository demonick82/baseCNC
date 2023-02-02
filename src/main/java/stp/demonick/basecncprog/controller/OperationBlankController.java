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
import stp.demonick.basecncprog.service.OperationBlankService;
import stp.demonick.basecncprog.service.ProgramService;

@Controller
@SessionAttributes({"detail", "program"})
public class OperationBlankController {
    private final ProgramService programService;
    private final OperationBlankService operationBlankService;

    public OperationBlankController(ProgramService programService, OperationBlankService operationBlankService) {
        this.programService = programService;
        this.operationBlankService = operationBlankService;
    }

    @GetMapping("/operationBlankView")
    public String viewOperationBlank(@RequestParam("id") long id,
                                     Model model) {
        Program program = programService.findProgramById(id);
        OperationBlank blank = program.getOperationBlank();
        String[] comment = blank.getComment().split(System.lineSeparator());
        model.addAttribute("program", program);
        model.addAttribute("comments", comment);
        return "operation_blank";
    }

    @GetMapping("/operationBlankPhoto/{programId}")
    public ResponseEntity<Resource> viewBlank(@PathVariable("programId") long id) {
        byte[] img = operationBlankService.downLoadImage(programService.findProgramById(id));
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(img.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(img));
    }

    @GetMapping("/operationBlankCreate")
    public String createOperationBlank(@RequestParam("id") long id,
                                       Model model) {
        model.addAttribute("program", programService.findProgramById(id));
        return "operation_blank_create";
    }

    @PostMapping("/addOperationBlank")
    public String addOperationBlank(@RequestParam("file") MultipartFile file,
                                    @RequestParam("id") long id,
                                    @RequestParam("comment") String comment,
                                    Model model) {
        operationBlankService.saveBlank(comment, file, id);
        Detail detail = (Detail) model.getAttribute("detail");
        if (detail != null) {
            return "redirect:/programs/?id=" + detail.getId();
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/editOperationBlank")
    public String openEditOperationBlank(@RequestParam("id") long id,
                                         Model model) {
        model.addAttribute("program", programService.findProgramById(id));
        return "operation_blank_edit";
    }

    @PostMapping("/editOperationBlank")
    public String editOperationBlank(@RequestParam("file") MultipartFile file,
                                     @RequestParam("id") long id,
                                     @RequestParam("comment") String comment,
                                     Model model) {
        if (file.isEmpty()) {
            operationBlankService.updateBlank(id, comment);
        } else {
            operationBlankService.saveBlank(comment, file, id);
        }
        Detail detail = (Detail) model.getAttribute("detail");
        if (detail != null) {
            return "redirect:/programs/?id=" + detail.getId();
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/deleteOperationBlank")
    public String deleteOperationBlank(@RequestParam("id") long id,
                                       Model model) {
        operationBlankService.deleteBlank(id);
        Detail detail = (Detail) model.getAttribute("detail");
        if (detail != null) {
            return "redirect:/programs/?id=" + detail.getId();
        } else {
            return "redirect:/";
        }
    }
}
