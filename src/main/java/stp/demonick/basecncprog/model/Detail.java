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
    private String drawingNumber;
    private String name;

    public static Detail of(String drawingNumber, String name) {
        Detail detail = new Detail();
        detail.drawingNumber = drawingNumber;
        detail.name = name;
        return detail;
    }

    @EqualsAndHashCode.Exclude
    private List<Program> programs = new ArrayList<>();

    public void addProgram(Program program) {
        programs.add(program);
    }
}
