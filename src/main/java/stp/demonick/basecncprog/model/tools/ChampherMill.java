package stp.demonick.basecncprog.model.tools;

import java.util.StringJoiner;

public class ChampherMill extends Tool {
    private double toolTapperAngle;
    private double chamferLength;


    public double getToolTapperAngle() {
        return toolTapperAngle;
    }

    public void setToolTapperAngle(double toolTapperAngle) {
        this.toolTapperAngle = Math.toDegrees(toolTapperAngle);
    }

    public double getChamferLength() {
        return chamferLength;
    }

    public void setChamferLength(double chamferLength) {
        this.chamferLength = chamferLength;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ChampherMill.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("toolNumber=" + getToolNumber())
                .add("toolName='" + getToolName() + "'")
                .add("toolDiametr=" + getToolDiametr())
                .add("length=" + getLength())
                .add("fluteLength=" + getFluteLength())
                .add("fluteNumber=" + getFluteNumber())
                .add("toolTapperAngle=" + toolTapperAngle)
                .add("chamferLength=" + chamferLength)
                .toString();
    }
}
