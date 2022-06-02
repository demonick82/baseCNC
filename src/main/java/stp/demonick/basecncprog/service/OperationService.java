package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import stp.demonick.basecncprog.model.Operation;
import stp.demonick.basecncprog.model.Program;
import stp.demonick.basecncprog.repository.MemProgramRepository;

import java.util.Collection;

@Service
public class OperationService {
    private final MemProgramRepository programRepository;


    public OperationService(MemProgramRepository programRepository) {
        this.programRepository = programRepository;
    }


    public Collection<Operation> findOperationsForProgramId(int id) {
        return programRepository.findById(id).getOperations();
    }

    public Collection<Operation> findAllOperations() {
        return null;
    }

}
