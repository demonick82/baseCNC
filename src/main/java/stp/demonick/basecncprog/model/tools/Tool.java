package stp.demonick.basecncprog.model.tools;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;
import java.util.StringJoiner;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "toolType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MillingTool.class, name = "Milling Tool-5 Parameters"),
        @JsonSubTypes.Type(value = DrillingTool.class, name = "Drilling Tool"),
        @JsonSubTypes.Type(value = SpotDrillingTool.class, name = "Spot Drill"),
        @JsonSubTypes.Type(value = ChampherMill.class, name = "Chamfer Mill"),
})

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Tool {
    private int id;
    private int toolNumber;
    private String toolName;
    private double toolDiametr;
    private double length;
    private double fluteLength;
    private int fluteNumber;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getToolNumber() {
        return toolNumber;
    }

    public void setToolNumber(int toolNumber) {
        this.toolNumber = toolNumber;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public double getToolDiametr() {
        return toolDiametr;
    }

    public void setToolDiametr(double toolDiametr) {
        this.toolDiametr = toolDiametr;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getFluteLength() {
        return fluteLength;
    }

    public void setFluteLength(double fluteLength) {
        this.fluteLength = fluteLength;
    }

    public int getFluteNumber() {
        return fluteNumber;
    }

    public void setFluteNumber(int fluteNumber) {
        this.fluteNumber = fluteNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tool tool = (Tool) o;
        return id == tool.id && toolNumber == tool.toolNumber && Objects.equals(toolName, tool.toolName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, toolNumber, toolName);
    }

}
