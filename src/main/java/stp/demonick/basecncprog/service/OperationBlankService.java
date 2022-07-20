package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.model.OperationBlank;
import stp.demonick.basecncprog.model.Program;
import stp.demonick.basecncprog.repository.OperationBlankRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class OperationBlankService {
    private final ProgramService programService;

    public OperationBlankService(ProgramService programService) {
        this.programService = programService;
    }

    public void saveBlank(String comment, MultipartFile file, Program program) {
        String path = program.getProgramPath() + "\\" + file.getOriginalFilename();
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(file.getBytes());
            program.setOperationBlank(new OperationBlank(path.replace("\\", "/"), comment));
            programService.saveProgram(program);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] downLoadImage(Program program) {
        try (FileInputStream fis = new FileInputStream(program.getOperationBlank().getPath())) {
            return fis.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}