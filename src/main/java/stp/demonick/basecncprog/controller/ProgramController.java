package stp.demonick.basecncprog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.model.Detail;

@Controller
public class ProgramController {


    @GetMapping({"/addProgramTest"})
    public String addTest() {
        return "add_pogramm_test";
    }

    @PostMapping("/testUpload")
    public String getFileTest(@RequestParam("file") MultipartFile file, @RequestParam("confirm1") String check) {
        System.out.println(file.getOriginalFilename());
        System.out.println("checkBox=" + check);
        return "index";
    }
}
