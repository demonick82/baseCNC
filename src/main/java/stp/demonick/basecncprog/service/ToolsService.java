package stp.demonick.basecncprog.service;

import org.springframework.stereotype.Service;
import stp.demonick.basecncprog.model.Operation;
import stp.demonick.basecncprog.model.tools.MillingTool;
import stp.demonick.basecncprog.model.tools.Tool;
import stp.demonick.basecncprog.repository.MemProgramRepository;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Service
public class ToolsService {

    private final MemProgramRepository programRepository;
    private Set<Tool> tools = new HashSet<>();

    public ToolsService(MemProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    private void findAllToolOfOperation(int id) {
        for (Operation operation : programRepository.findById(id).getOperations()) {
            tools.add(operation.getTool());
        }
    }

    public Set<MillingTool> findMillingTools(int id) {
        Set<MillingTool> millingTools=new TreeSet<>(Comparator.comparingInt(MillingTool::getToolNumber));
        findAllToolOfOperation(id);
        for (Tool tool : tools) {
            if (tool.getClass().equals("MillingTool")) {
                millingTools.add((MillingTool) tool);
            }
        }
        return millingTools;
    }
}
