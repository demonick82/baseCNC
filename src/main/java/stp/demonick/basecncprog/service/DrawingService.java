package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.model.Drawing;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class DrawingService {
    private final DetailService detailService;

    public DrawingService(DetailService detailService) {
        this.detailService = detailService;
    }

    public void saveDrawing(MultipartFile file, Detail detail) {
        String pathDrawing = Paths.get("D:\\work\\BaseCNC\\drawings", file.getOriginalFilename()).toString();
        System.out.println("path====");
        try (FileOutputStream fos = new FileOutputStream(pathDrawing)) {
            fos.write(file.getBytes());
            detail.setDrawing(new Drawing(pathDrawing.replace("\\", "/")));
            detailService.save(detail);
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
