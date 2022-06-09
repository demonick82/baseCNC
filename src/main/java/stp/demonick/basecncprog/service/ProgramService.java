package stp.demonick.basecncprog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.model.Program;
import stp.demonick.basecncprog.repository.MemDetailRepository;
import stp.demonick.basecncprog.repository.MemProgramRepository;
import stp.demonick.basecncprog.utils.CopyFiles;
import stp.demonick.basecncprog.utils.TextFormat;

import java.awt.*;
import java.io.*;
import java.util.Collection;

@Service
public class ProgramService {
    private final MemDetailRepository detailRepository;
    private final MemProgramRepository programRepository;
    private final TextFormat format;
    private final CopyFiles copyFiles;


    public ProgramService(MemDetailRepository detailRepository, MemProgramRepository programRepository,
                          TextFormat format, CopyFiles copyFiles) {
        this.detailRepository = detailRepository;
        this.programRepository = programRepository;
        this.format = format;
        this.copyFiles = copyFiles;
    }

    public Collection<Program> findAllProgram() {
        return programRepository.findAll();
    }

    public Collection<Program> findAllProgramForDetailId(int id) {
        return detailRepository.findById(id).getPrograms();
    }

    public Program findProgramById(int id) {
        return programRepository.findById(id);
    }

    public void addProgram(MultipartFile file, int id) {
        StringBuilder builder = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = file.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "Cp1251"));
            reader.lines().forEach(builder::append);
            Program program = mapper.readValue(format.updateJson(builder), Program.class);

            Detail detail = detailRepository.findById(id);
            detail.addProgram(program);
            programRepository.setIdOperations(program);
            String newPrtDir = copyFiles.copyPrtFiles(program.getModelPath(), detail.getDrawingNumber(),
                    program.getProgramName(), program.getMachine().getMachineName());
            String newCNCDir = copyFiles.copyCNCFiles(program.getModelPath(), program.getProgramPath(), detail.getDrawingNumber(),
                    program.getProgramName(), program.getMachine().getMachineName());
            program.setModelPath(newPrtDir);
            program.setProgramPath(newCNCDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFile(String path) {
        try {
            new ProcessBuilder("explorer.exe",  path).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
