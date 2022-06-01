package stp.demonick.basecncprog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import stp.demonick.basecncprog.model.tools.Tool;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Operation {
    private int id;
    private String programmNumber;
    private String path;
    private String operationName;
    private String operationType;
    private double spindleSpeed;
    private double feedRate;
    private double stockPart;
    private double stockFloor;
    private double machineTime;
    private Tool tool;


}
