package stp.demonick.basecncprog.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import stp.demonick.basecncprog.model.OperationBlank;
import stp.demonick.basecncprog.model.Program;
import stp.demonick.basecncprog.service.OperationBlankService;
import stp.demonick.basecncprog.service.ProgramService;

@Controller
@SessionAttributes({"detail", "program"})
public class OperationBlankControl {
    private final ProgramService programService;
    private final OperationBlankService operationBlankService;

    public OperationBlankControl(ProgramService programService, OperationBlankService operationBlankService) {
        this.programService = programService;
        this.operationBlankService = operationBlankService;
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
}
