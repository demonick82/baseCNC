package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import stp.demonick.basecncprog.exceptions.NotFoundException;
import stp.demonick.basecncprog.model.Operation;
import stp.demonick.basecncprog.model.tools.Tool;
import stp.demonick.basecncprog.repository.ProgramRepository;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

@Service
public class ToolsService {

    private final ProgramRepository programRepository;

    public ToolsService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public Set<? super Tool> findCustomTool(long id, Predicate<String> predicate) {
        Set<? super Tool> otherTools = new TreeSet<>(Comparator.comparingInt(Tool::getToolNumber));
        for (Operation operation : programRepository.findById(id).orElseThrow(() ->
                new NotFoundException("operation not found")).getOperations()) {
            if (predicate.test(operation.getTool().getClass().getSimpleName())) {
                otherTools.add(operation.getTool());
            }
        }
        return otherTools;
    }
}
