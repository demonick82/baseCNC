package stp.demonick.basecncprog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stp.demonick.basecncprog.exceptions.NotFoundException;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.model.Program;
import stp.demonick.basecncprog.repository.DetailRepository;
import stp.demonick.basecncprog.repository.ProgramRepository;
import stp.demonick.basecncprog.utils.CopyFiles;
import stp.demonick.basecncprog.utils.TextFormat;

import java.io.*;
import java.util.Collection;

@Service
public class ProgramService {
    private final DetailRepository detailRepository;
    private final ProgramRepository programRepository;
    private final TextFormat format;
    private final CopyFiles copyFiles;
    private final ProgrammerService programmerService;
    private final MachineService machineService;

    public ProgramService(DetailRepository detailRepository, ProgramRepository programRepository, TextFormat format, CopyFiles copyFiles, ProgrammerService programmerService, MachineService machineService) {
        this.detailRepository = detailRepository;
        this.programRepository = programRepository;
        this.format = format;
        this.copyFiles = copyFiles;
        this.programmerService = programmerService;
        this.machineService = machineService;
    }


    public Collection<Program> findAllProgram() {
        return programRepository.findAll();
    }

    public Collection<Program> findAllProgramForDetailId(long id) {
        return detailRepository.findById(id).orElseThrow(
                () -> new NotFoundException("program not found")).getPrograms();
    }

    public Program findProgramById(long id) {
        return programRepository.findById(id).orElseThrow(
                () -> new NotFoundException("program not found"));
    }

    public void addProgram(MultipartFile file, long id) {
        StringBuilder builder = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = file.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "Cp1251"));
            reader.lines().forEach(builder::append);
            Program program = mapper.readValue(format.updateJson(builder), Program.class);
            program.setProgrammer(programmerService.findByLogin("polyanskiy"));
            program.setMachine(machineService.findMachineByNXName(program.getMachine().getNameForNX()));
            Detail detail = detailRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("detail not found"));
            detail.addProgram(program);
            detailRepository.save(detail);
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
            new ProcessBuilder("explorer.exe", path).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteProgram(long id) {
        programRepository.deleteById(id);
    }
}
