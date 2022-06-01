package stp.demonick.basecncprog.model.tools;

import java.util.StringJoiner;

public class MillingTool extends Tool {
    private double cornerRadius;

    private static MillingTool of(int toolNumber, String toolName, double toolDiametr, double length,
                                  double cornerRadius, double fluteLength, int fluteNumber) {
        MillingTool millingTool = new MillingTool();
        millingTool.setFluteNumber(toolNumber);
        millingTool.setToolName(toolName);
        millingTool.setToolDiametr(toolDiametr);
        millingTool.setLength(length);
        millingTool.setFluteLength(fluteLength);
        millingTool.setFluteNumber(fluteNumber);
        millingTool.cornerRadius = cornerRadius;
        return millingTool;
    }

    public double getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(double cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MillingTool.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("toolNumber=" + getToolNumber())
                .add("toolName='" + getToolName() + "'")
                .add("toolDiametr=" + getToolDiametr())
                .add("cornerRadius=" + cornerRadius)
                .add("length=" + getLength())
                .add("fluteLength=" + getFluteLength())
                .add("fluteNumber=" + getFluteNumber())
                .toString();
    }
}
