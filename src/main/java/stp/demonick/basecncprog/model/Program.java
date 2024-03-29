package stp.demonick.basecncprog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity
@Table(name = "programs")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String programName;
    private String programPath;
    private String ugVersion;
    private String modelPath;
    private String fullModelPath;
    private String fullInfFilePath;


    private LocalDate created;
    private LocalDate updated;
    @ManyToOne
    @JoinColumn(name = "machines_id")
    private Machine machine;

    @EqualsAndHashCode.Exclude
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "operation_blanks_id")
    private OperationBlank operationBlank;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "programmers_id")
    private User user;


    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "operations_id")
    private List<Operation> operations = new ArrayList<>();

    public void addOperation(Operation operation) {
        operations.add(operation);
    }
}