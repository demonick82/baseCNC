package stp.demonick.basecncprog.model.tools;

import java.util.Objects;
import java.util.StringJoiner;

public class TapTool extends Tool {
    private double pitch;

    private static TapTool of(int toolNumber, String toolName, double toolDiametr, double length,
                              double fluteLength, int fluteNumber, double pitch) {
        TapTool tapTool = new TapTool();
        tapTool.setFluteNumber(toolNumber);
        tapTool.setToolName(toolName);
        tapTool.setToolDiametr(toolDiametr);
        tapTool.setLength(length);
        tapTool.setFluteLength(fluteLength);
        tapTool.setFluteNumber(fluteNumber);
        tapTool.pitch = pitch;
        return tapTool;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TapTool.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("toolNumber=" + getToolNumber())
                .add("toolName='" + getToolName() + "'")
                .add("toolDiametr=" + getToolDiametr())
                .add("length=" + getLength())
                .add("fluteLength=" + getFluteLength())
                .add("fluteNumber=" + getFluteNumber())
                .add("pitch=" + pitch)
                .toString();
    }
}
