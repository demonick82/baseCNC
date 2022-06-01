package stp.demonick.basecncprog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Detail {
    private int id;
    private String partName;
    private int versionId;

    @EqualsAndHashCode.Exclude
    private String path;

    @EqualsAndHashCode.Exclude
    private List<Program> programs = new ArrayList<>();

    public void addMachine(Program program) {
        programs.add(program);
    }
}
