package stp.demonick.basecncprog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Program {
    private int id;
    private String programName;
    private String programPath;
    private String ugVersion;
    private String modelPath;
    private Machine machine;

    @EqualsAndHashCode.Exclude
    private OperationBlank operationBlank;
    private Programmer programmer;


    @EqualsAndHashCode.Exclude
    private List<Operation> operations = new ArrayList<>();


    public void addOperation(Operation operation) {
        operations.add(operation);
    }
}
