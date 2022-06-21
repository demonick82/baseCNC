package stp.demonick.basecncprog.repository;

import org.springframework.stereotype.Repository;
import stp.demonick.basecncprog.model.Detail;
import stp.demonick.basecncprog.model.Operation;
import stp.demonick.basecncprog.model.Program;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

//@Repository
public class MemProgramRepository implements Store<Program> {

    private final MemDetailRepository repository;
    private static final AtomicInteger programsId = new AtomicInteger(0);
    private static final AtomicInteger operationsId = new AtomicInteger(0);
    private static final AtomicInteger toolsId = new AtomicInteger(0);

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
    public Program findById(long id) {
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
    public void delete(long id) {

    }

    public void setIdOperations(Program program) {
        program.setId(programsId.incrementAndGet());
        for (Operation operation : program.getOperations()) {
            operation.setId(operationsId.incrementAndGet());
            operation.getTool().setId(toolsId.incrementAndGet());
        }
    }

}
