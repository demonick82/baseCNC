package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import stp.demonick.basecncprog.model.Operation;
import stp.demonick.basecncprog.model.tools.DrillingTool;
import stp.demonick.basecncprog.model.tools.MillingTool;
import stp.demonick.basecncprog.model.tools.Tool;
import stp.demonick.basecncprog.repository.MemProgramRepository;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

@Service
public class ToolsService {

    private final MemProgramRepository programRepository;

    public ToolsService(MemProgramRepository programRepository) {
        this.programRepository = programRepository;
    }
    public Set<? super Tool> findCustomTool(int id, Predicate<String> predicate) {
        Set<? super Tool> otherTools = new TreeSet<>(Comparator.comparingInt(Tool::getToolNumber));
        for (Operation operation : programRepository.findById(id).getOperations()) {
            if (predicate.test(operation.getTool().getClass().getSimpleName())) {
                otherTools.add(operation.getTool());
            }
        }
        return otherTools;
    }
}
