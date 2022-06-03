package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import stp.demonick.basecncprog.model.Operation;
import stp.demonick.basecncprog.model.tools.Tool;
import stp.demonick.basecncprog.repository.MemProgramRepository;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Service
public class ToolsService {

    private final MemProgramRepository programRepository;

    public ToolsService(MemProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public Set<Tool> findAllToolOfOperation(int id) {
        Set<Tool> tools = new TreeSet<>(Comparator.comparingInt(Tool::getToolNumber));
        for (Operation operation : programRepository.findById(id).getOperations()) {
            tools.add(operation.getTool());
        }
        return tools;
    }
}
