package stp.demonick.basecncprog.repository;

import org.springframework.stereotype.Repository;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.model.Program;

import java.util.*;

@Repository
public class MemProgramRepository implements Store<Program> {

    private final MemDetailRepository repository;


    public MemProgramRepository(MemDetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Program> findAll() {
        List<Program> programs = new ArrayList<>();
        for (Detail detail : repository.findAll()) {
            programs.addAll(detail.getPrograms());
        }
        return programs;
    }

    @Override
    public void save(Program domain) {

    }

    @Override
    public Program findById(int id) {
        for (Detail detail : repository.findAll()) {
            for (Program program : detail.getPrograms()) {
                if (program.getId() == id) {
                    return program;
                }
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {

    }

}
