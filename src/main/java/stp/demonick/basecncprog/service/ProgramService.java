package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import stp.demonick.basecncprog.model.Program;
import stp.demonick.basecncprog.repository.MemDetailRepository;
import stp.demonick.basecncprog.repository.MemProgramRepository;

import java.util.Collection;

@Service
public class ProgramService {
    private final MemDetailRepository detailRepository;
    private final MemProgramRepository programRepository;


    public ProgramService(MemDetailRepository detailRepository, MemProgramRepository programRepository) {
        this.detailRepository = detailRepository;
        this.programRepository = programRepository;
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
}
