package stp.demonick.basecncprog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "details")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String drawingNumber;
    private String name;

    public static Detail of(String drawingNumber, String name) {
        Detail detail = new Detail();
        detail.drawingNumber = drawingNumber;
        detail.name = name;
        return detail;
    }

    @EqualsAndHashCode.Exclude
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "drawings_id")
    private Drawing drawing;

    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model3d_id")
    private Model3D model3D;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "details_id")
    private List<TechnologicalProcess> technologicalProcesses = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "details_id")
    private List<Program> programs = new ArrayList<>();

    public void addProgram(Program program) {
        programs.add(program);
    }

}
