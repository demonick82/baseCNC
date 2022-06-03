package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import stp.demonick.basecncprog.model.tools.Tool;
import stp.demonick.basecncprog.service.ProgramService;
import stp.demonick.basecncprog.service.ToolsService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

@Controller
@SessionAttributes(value = "detailName")
public class ToolListController {

    private final ToolsService toolsService;

    public ToolListController(ToolsService toolsService) {
        this.toolsService = toolsService;
    }

    @GetMapping({"/tools"})
    public String viewTool(@RequestParam("id") int id, Model model) {
        toolsService.findMillingTools(id).forEach(System.out::println);
        model.addAttribute("tools", toolsService.findMillingTools(id));
        return "tool_list";
    }
}
