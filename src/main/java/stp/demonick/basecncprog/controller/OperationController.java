package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import stp.demonick.basecncprog.model.Operation;
import stp.demonick.basecncprog.model.Program;
import stp.demonick.basecncprog.service.OperationService;
import stp.demonick.basecncprog.service.ProgramService;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@SessionAttributes("detail")
public class OperationController {
    private final ProgramService programService;
    private final OperationService operationService;

    public OperationController(ProgramService programService, OperationService operationService) {
        this.programService = programService;
        this.operationService = operationService;
    }


    @GetMapping({"/operations"})
    public String viewOperations(@RequestParam("id") long id, Model model) {
        List<Operation> operations = operationService.findOperationsForProgramId(id);
        double machineTime = operations.stream()
                .mapToDouble(Operation::getMachineTime).sum();
        model.addAttribute("program", programService.findProgramById(id));
        model.addAttribute("operations", operations);
        model.addAttribute("increment", new AtomicInteger(0));
        model.addAttribute("machineTime", machineTime);
        return "operations_list";
    }

    @GetMapping("/editOperation")
    public String openEditOperation(@RequestParam("id") long id, Model model) {
        Program program = programService.findProgramById(id);
        List<Operation> operations = program.getOperations();
        operations.sort(Comparator.comparing(Operation::getProgramNumber));
        model.addAttribute("program", program);
        model.addAttribute("operations", operations);
        model.addAttribute("increment", new AtomicInteger(0));
        return "edit_operation";
    }

    @PostMapping("/editOperation")
    public String editOperation(@RequestParam("id") long id,
                                @RequestParam("operationName") String[] operationName,
                                @RequestParam("description") String[] description) {
        operationService.updateOperations(id, operationName, description);
        return "redirect:/";
    }
}
