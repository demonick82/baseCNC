package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.exceptions.NotFoundException;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.model.Drawing;
import stp.demonick.basecncprog.model.OperationBlank;
import stp.demonick.basecncprog.model.Program;
import stp.demonick.basecncprog.repository.DetailRepository;
import stp.demonick.basecncprog.utils.Translit;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;

@Service
public class DetailService {
    private final DetailRepository detailRepository;

    public DetailService(DetailRepository detailRepository ) {
        this.detailRepository = detailRepository;
    }


    public Collection<Detail> findAllDetails() {
        return detailRepository.findByOrderByDrawingNumberAsc();
    }

/*    public void save(Detail detail, MultipartFile file) {
        String pathDrawing = Paths.get("D:\\work\\BaseCNC\\drawings", file.getOriginalFilename()).toString();
        try (FileOutputStream fos = new FileOutputStream(pathDrawing)) {
            fos.write(file.getBytes());
            detail.setDrawing(new Drawing(pathDrawing.replace("\\", "/")));
            detailRepository.save(detail);
        } catch (IOException e) {
            detailRepository.save(detail);
            System.out.println("without drawing");
        }
    }*/
    public void save(Detail detail) {
        detailRepository.save(detail);
    }


    public Detail findDetailById(long id) {
        return detailRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Detail not found"));
    }

    public void deleteDetail(long id) {
        detailRepository.deleteById(id);
    }
    public byte[] downLoadDrawing(Detail detail) {
        try (FileInputStream fis = new FileInputStream(detail.getDrawing().getPath())) {
            return fis.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
