package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import stp.demonick.basecncprog.service.OperationService;
import stp.demonick.basecncprog.service.ProgramService;

import java.time.LocalDate;

@Controller
@SessionAttributes("detail")
public class OperationsListControl {

    private final ProgramService programService;
    private final OperationService operationService;

    public OperationsListControl(ProgramService programService, OperationService operationService) {
        this.programService = programService;
        this.operationService = operationService;
    }

    @GetMapping({"/operations"})
    public String viewOperations(@RequestParam("id") int id, Model model) {
        model.addAttribute("program", programService.findProgramById(id));
        model.addAttribute("operations", operationService.findOperationsForProgramId(id));
        model.addAttribute("date", LocalDate.now());
        return "operations_list";
    }

}
