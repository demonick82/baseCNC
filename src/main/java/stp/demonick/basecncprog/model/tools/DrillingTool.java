package stp.demonick.basecncprog.model.tools;

import java.util.StringJoiner;

public class DrillingTool extends Tool {
    private double toolPointAngle;

    private static DrillingTool of(int toolNumber, String toolName, double toolDiametr, double length,
                              double fluteLength, int fluteNumber, double toolPointAngle) {
        DrillingTool drillingTool = new DrillingTool();
        drillingTool.setFluteNumber(toolNumber);
        drillingTool.setToolName(toolName);
        drillingTool.setToolDiametr(toolDiametr);
        drillingTool.setLength(length);
        drillingTool.setFluteLength(fluteLength);
        drillingTool.setFluteNumber(fluteNumber);
        drillingTool.toolPointAngle = toolPointAngle;
        return drillingTool;
    }

    public double getToolPointAngle() {
        return toolPointAngle;
    }

    public void setToolPointAngle(double toolPointAngle) {
        this.toolPointAngle = toolPointAngle;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DrillingTool.class.getSimpleName() + "[", "]")
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
