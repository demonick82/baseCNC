package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import stp.demonick.basecncprog.exceptions.NotFoundException;
import stp.demonick.basecncprog.model.Operation;
import stp.demonick.basecncprog.repository.OperationRepository;
import stp.demonick.basecncprog.repository.ProgramRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Service
public class OperationService {
    private final ProgramRepository programRepository;
    private final OperationRepository operationRepository;

    public OperationService(ProgramRepository programRepository, OperationRepository operationRepository) {
        this.programRepository = programRepository;
        this.operationRepository = operationRepository;
    }


    public List<Operation> findOperationsForProgramId(long id) {
        List<Operation> operations=programRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Operations not found")).getOperations();
        operations.sort(Comparator.comparing(Operation::getProgramNumber));
        return operations;
    }

    public Operation findOperationById(long id) {
        return operationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("operation not found"));
    }
}
