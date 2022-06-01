package stp.demonick.basecncprog.model.tools;

import java.util.StringJoiner;

public class ThreadMillTool extends Tool {
    private double pitch;

    private static ThreadMillTool of(int toolNumber, String toolName, double toolDiametr, double length,
                                     double fluteLength, int fluteNumber, double pitch) {
        ThreadMillTool threadMillTool = new ThreadMillTool();
        threadMillTool.setToolNumber(toolNumber);
        threadMillTool.setToolName(toolName);
        threadMillTool.setToolDiametr(toolDiametr);
        threadMillTool.setLength(length);
        threadMillTool.setFluteLength(fluteLength);
        threadMillTool.setFluteNumber(fluteNumber);
        threadMillTool.pitch = pitch;
        return threadMillTool;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", ThreadMillTool.class.getSimpleName() + "[", "]")
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
