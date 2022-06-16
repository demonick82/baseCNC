package stp.demonick.basecncprog.model.tools;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "toolType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MillingTool.class, name = "Milling Tool-5 Parameters"),
        @JsonSubTypes.Type(value = DrillingTool.class, name = "Drilling Tool"),
        @JsonSubTypes.Type(value = SpotDrillingTool.class, name = "Spot Drill"),
        @JsonSubTypes.Type(value = ChampherMill.class, name = "Chamfer Mill"),
        @JsonSubTypes.Type(value = ThreadMillTool.class, name = "Thread Mill"),
        @JsonSubTypes.Type(value = TapTool.class, name = "Tap"),
        @JsonSubTypes.Type(value = BoreTool.class, name = "Расточной резец"),
        @JsonSubTypes.Type(value = TCutterMillTool.class, name = "Milling Tool-T Cutter"),
})

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Tool {
    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private int toolNumber;
    private String toolName;
    private double toolDiametr;
    private double length;
    private double fluteLength;
    private int fluteNumber;

    public void setToolName(String toolName) {
        this.toolName = toolName.replace("_", " ");
    }
}
