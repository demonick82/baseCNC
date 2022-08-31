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
import stp.demonick.basecncprog.service.DetailService;

@Controller
@SessionAttributes({"detail", "program"})
public class DrawingViewControl {

    private final DetailService detailService;

    public DrawingViewControl(DetailService detailService) {
        this.detailService = detailService;
    }


    @GetMapping("/drawingView")
    String drawingView( @RequestParam("id") long id,Model model) {
        model.addAttribute("id", id);
        return "drawing_view";
    }

    @GetMapping("/viewDrawing/{detailId}")
    public ResponseEntity<Resource> viewDrawing(@PathVariable("detailId") long id) {
        byte[] img = detailService.downLoadDrawing(detailService.findDetailById(id));
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(img.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new ByteArrayResource(img));
    }
}
