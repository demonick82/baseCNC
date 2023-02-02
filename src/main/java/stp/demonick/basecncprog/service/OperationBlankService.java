package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.model.OperationBlank;
import stp.demonick.basecncprog.model.Program;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OperationBlankService {
    private final ProgramService programService;

    public OperationBlankService(ProgramService programService) {
        this.programService = programService;
    }

    public void saveBlank(String comment, MultipartFile file, long id) {
        Program program = programService.findProgramById(id);
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

    public void updateBlank(long id, String comment) {
        Program program = programService.findProgramById(id);
        program.getOperationBlank().setComment(comment);
        programService.saveProgram(program);
    }

    public void deleteBlank(long id) {
        Program program = programService.findProgramById(id);
        try {
            Files.delete(Path.of(program.getOperationBlank().getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        program.setOperationBlank(null);
        programService.saveProgram(program);
    }
}