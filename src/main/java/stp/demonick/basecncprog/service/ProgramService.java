package stp.demonick.basecncprog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.model.Program;
import stp.demonick.basecncprog.repository.MemDetailRepository;
import stp.demonick.basecncprog.repository.MemProgramRepository;
import stp.demonick.basecncprog.utils.TextFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

@Service
public class ProgramService {
    private final MemDetailRepository detailRepository;
    private final MemProgramRepository programRepository;
    private final TextFormat format;



    public ProgramService(MemDetailRepository detailRepository, MemProgramRepository programRepository, TextFormat format) {
        this.detailRepository = detailRepository;
        this.programRepository = programRepository;
        this.format = format;
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
    public void addProgram(MultipartFile file ,int id) {
        StringBuilder builder = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = file.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "Cp1251"));
            reader.lines().forEach(builder::append);
            Program program = mapper.readValue(format.updateJson(builder), Program.class);
            programRepository.setIdOperations(program);
            detailRepository.findById(id).addProgram(program);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
