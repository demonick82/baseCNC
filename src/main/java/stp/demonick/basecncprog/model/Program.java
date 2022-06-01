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
    private Machine machine;
    private String programPath;
    private String ugVersion;
    private String machineName;

    @EqualsAndHashCode.Exclude
    private List<Operation> operations = new ArrayList<>();


    public void addOperation(Operation operation) {
        operations.add(operation);
    }


}
