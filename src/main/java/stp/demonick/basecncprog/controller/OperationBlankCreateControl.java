package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.model.Program;
import stp.demonick.basecncprog.service.OperationBlankService;

import javax.servlet.http.HttpServletRequest;


@Controller
@SessionAttributes({"detail", "program"})
public class OperationBlankCreateControl {

    private final OperationBlankService operationBlankService;

    public OperationBlankCreateControl(OperationBlankService operationBlankService) {
        this.operationBlankService = operationBlankService;
    }

    @PostMapping("/addOperationBlank")
    public String addOperationBlank(@RequestParam("file") MultipartFile file,
                                    Model model, HttpServletRequest req) {
        Program program = (Program) model.getAttribute("program");
        operationBlankService.saveBlank(req.getParameter("comment"), file, program);
        Detail detail = (Detail) model.getAttribute("detail");
        return "redirect:/programs/?id=" + detail.getId();
    }
}
