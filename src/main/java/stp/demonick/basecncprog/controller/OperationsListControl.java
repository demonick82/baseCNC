package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import stp.demonick.basecncprog.model.Operation;
import stp.demonick.basecncprog.model.Program;
import stp.demonick.basecncprog.service.OperationService;
import stp.demonick.basecncprog.service.ProgramService;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
        List<Operation> operations = operationService.findOperationsForProgramId(id);
        double machineTime = operations.stream()
                .mapToDouble(Operation::getMachineTime).sum();
        model.addAttribute("program", programService.findProgramById(id));
        model.addAttribute("operations", operations);
        model.addAttribute("increment", new AtomicInteger(0));
        model.addAttribute("machineTime", machineTime);
        return "operations_list";
    }
}
