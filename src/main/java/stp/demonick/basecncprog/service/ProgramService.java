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
import stp.demonick.basecncprog.utils.StartPath;
import stp.demonick.basecncprog.utils.TextFormat;
import stp.demonick.basecncprog.utils.Translit;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class ProgramService {
    private final DetailRepository detailRepository;
    private final ProgramRepository programRepository;
    private final TextFormat format;
    private final CopyFiles copyFiles;
    private final UsersService usersService;
    private final MachineService machineService;
    private final Translit translit;
    private final StartPath startPath;


    public ProgramService(DetailRepository detailRepository, ProgramRepository programRepository,
                          TextFormat format, CopyFiles copyFiles, UsersService usersService,
                          MachineService machineService, Translit translit, StartPath startPath) {
        this.detailRepository = detailRepository;
        this.programRepository = programRepository;
        this.format = format;
        this.copyFiles = copyFiles;
        this.usersService = usersService;
        this.machineService = machineService;
        this.translit = translit;
        this.startPath = startPath;
    }

    public List<Program> findAllProgramForDetailId(long id) {
        List<Program> programList = detailRepository.findById(id).orElseThrow(
                () -> new NotFoundException("program not found")).getPrograms();
        programList.sort(Comparator.comparing(Program::getProgramName));
        return programList;
    }

    public Program findProgramById(long id) {
        return programRepository.findById(id).orElseThrow(
                () -> new NotFoundException("program not found"));
    }

    public void addProgram(MultipartFile file, long id, String login) {
        StringBuilder builder = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = file.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "Cp1251"));
            reader.lines().forEach(builder::append);
            Program program = mapper.readValue(format.updateJson(builder), Program.class);
            program.setUser(usersService.findByLogin(login));
            program.setMachine(machineService.findMachineByName(program.getMachine().getMachineName()));
            program.setCreated(LocalDate.now());
            program.setFullInfFilePath(program.getProgramPath() + file.getOriginalFilename());
            Detail detail = detailRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("detail not found"));
            detail.addProgram(program);
            String newPrtDir = copyFiles.copyPrtFiles(program.getModelPath(), login, translit.setLatin(detail.getDrawingNumber()),
                    program.getProgramName(), program.getMachine().getMachineName());
            String newCNCDir = copyFiles.copyCNCFiles(program.getModelPath(), login, program.getProgramPath(),
                    translit.setLatin(detail.getDrawingNumber()), program.getProgramName(), program.getMachine().getMachineName());
            program.setFullModelPath(program.getModelPath());
            program.setModelPath(newPrtDir);
            program.setProgramPath(newCNCDir);
            detailRepository.save(detail);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Выберете файл");
        } catch (IOException e) {
            System.out.println("Неверный формат файла");
        }
    }

    public void openFile(String path) {
        try {
            new ProcessBuilder("explorer.exe", path).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteDirectory(Path path) {
        try {
            Files.walk(path)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProgram(long id) {
        Program program = programRepository.findById(id).orElseThrow(
                () -> new NotFoundException("program not found"));
        Path fullPath = Paths.get(program.getModelPath());
        if (Files.exists(fullPath)) {
            Path newPath = Paths.get(startPath.loadStartPath(), fullPath.getName(4).toString(),
                    fullPath.getName(5).toString(), fullPath.getName(6).toString(),
                    fullPath.getName(7).toString(), fullPath.getName(8).toString());
            deleteDirectory(newPath);
        }
        programRepository.delete(program);
    }

    public void saveProgram(Program program) {
        programRepository.save(program);
    }
}
