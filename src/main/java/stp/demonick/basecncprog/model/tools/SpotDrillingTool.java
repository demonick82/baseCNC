package stp.demonick.basecncprog.model.tools;


import java.util.Objects;
import java.util.StringJoiner;

public class SpotDrillingTool extends Tool {
    private double toolPointAngle;

    public double getToolPointAngle() {
        return toolPointAngle;
    }

    public void setToolPointAngle(double toolPointAngle) {
        this.toolPointAngle = toolPointAngle;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SpotDrillingTool.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("toolNumber=" + getToolNumber())
                .add("toolName='" + getToolName() + "'")
                .add("toolDiametr=" + getToolDiametr())
                .add("length=" + getLength())
                .add("fluteLength=" + getFluteLength())
                .add("fluteNumber=" + getFluteNumber())
                .add("toolPointAngle=" + toolPointAngle)
                .toString();
    }
}
