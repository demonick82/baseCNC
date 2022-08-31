package stp.demonick.basecncprog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.service.DrawingService;

@Controller
@SessionAttributes({"detail", "program"})
public class AddDrawingControl {
    private final DrawingService drawingService;

    public AddDrawingControl(DrawingService drawingService) {
        this.drawingService = drawingService;
    }

    @GetMapping("/addDrawing")
    String AddDrawing() {
        return "add_drawing";
    }

    @PostMapping("/uploadDrawing")
    String uploadDrawing(@RequestParam("file") MultipartFile file,
                         Model model) {
        Detail detail = (Detail) model.getAttribute("detail");
        if (detail != null) {
            drawingService.saveDrawing(file, detail);
        }
        return "redirect:/";
    }
}
