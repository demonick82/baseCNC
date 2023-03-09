package stp.demonick.basecncprog.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.service.DetailService;
import stp.demonick.basecncprog.service.DrawingService;

@Controller
@SessionAttributes({"detail", "program"})
public class DrawingController {
    private final DrawingService drawingService;
    private final DetailService detailService;


    public DrawingController(DrawingService drawingService, DetailService detailService) {
        this.drawingService = drawingService;
        this.detailService = detailService;
    }

    @GetMapping("/addDrawing")
    String AddDrawing() {
        return "create_drawing";
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

    @GetMapping("/drawingView")
    String drawingView( @RequestParam("id") long id,Model model) {
        model.addAttribute("id", id);
        return "drawing_view";
    }

    @GetMapping("/viewDrawing/{detailId}")
    public ResponseEntity<byte[]> viewDrawing(@PathVariable("detailId") long id) {
        byte[] img = detailService.downLoadDrawing(detailService.findDetailById(id));
        return ResponseEntity.ok()
                .contentLength(img.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(img);
    }
}
