package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import stp.demonick.basecncprog.service.ToolsService;

@Controller
@SessionAttributes(value = "detail")
public class ToolController {

    private final ToolsService toolsService;

    public ToolController(ToolsService toolsService) {
        this.toolsService = toolsService;
    }


    @GetMapping({"/tools"})
    public String viewTool(@RequestParam("id") long id, Model model) {
        model.addAttribute("millingTools", toolsService.findCustomTool(id,x->x.equals("MillingTool")));
        model.addAttribute("drillingTools", toolsService.findCustomTool(id,x->x.equals("DrillingTool")));
        model.addAttribute("spotDrillingTools", toolsService.findCustomTool(id,x->x.equals("SpotDrillingTool")));
        model.addAttribute("champherMills", toolsService.findCustomTool(id,x->x.equals("ChampherMill")));
        model.addAttribute("tapTools", toolsService.findCustomTool(id,x->x.equals("TapTool")));
        model.addAttribute("threadMillTools", toolsService.findCustomTool(id,x->x.equals("ThreadMillTool")));
        model.addAttribute("boreTools", toolsService.findCustomTool(id,x->x.equals("BoreTool")));
        model.addAttribute("tCutterMills", toolsService.findCustomTool(id,x->x.equals("TCutterMillTool")));
        return "tool_list";
    }
}
