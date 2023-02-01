package stp.demonick.basecncprog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import stp.demonick.basecncprog.model.tools.Tool;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity
@Table(name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String programNumber;
    private String operationName;
    private String operationType;
    private double spindleSpeed;
    private double feedRate;
    private double stockPart;
    private double stockFloor;
    private double cutDepth;
    private double machineTime;
    private String description;
    @OneToOne (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tools_id")
    private Tool tool;

    public void setOperationName(String operationName) {
        this.operationName = operationName.replace("_", " ").trim();
    }
}
