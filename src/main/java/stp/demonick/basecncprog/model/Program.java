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
    private LocalDate created;
    private LocalDate updated;
    @OneToOne
    private Machine machine;

    @EqualsAndHashCode.Exclude
    @OneToOne
    private OperationBlank operationBlank;
    @OneToOne
    @EqualsAndHashCode.Exclude
    private Programmer programmer;


    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "programs_id")
    private List<Operation> operations = new ArrayList<>();


    public void addOperation(Operation operation) {
        operations.add(operation);
    }
}
