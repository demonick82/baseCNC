package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import stp.demonick.basecncprog.exceptions.NotFoundException;
import stp.demonick.basecncprog.model.Operation;
import stp.demonick.basecncprog.model.Program;
import stp.demonick.basecncprog.repository.ProgramRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class OperationService {
    private final ProgramRepository programRepository;

    public OperationService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }


    public Collection<Operation> findOperationsForProgramId(long id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Operations not found")).getOperations();
    }

    public Collection<Operation> findAllOperations() {
        return null;
    }

}
