package stp.demonick.basecncprog.model.tools;

import java.util.StringJoiner;

public class ChampherMill extends Tool {
    private double toolTapperAngle;
    private double chamferLength;

    private static ChampherMill of(int toolNumber, String toolName, double toolDiametr, double length,
                                   double fluteLength, int fluteNumber, double toolTapperAngle,double chamferLength) {
        ChampherMill champherMill = new ChampherMill();
        champherMill.setFluteNumber(toolNumber);
        champherMill.setToolName(toolName);
        champherMill.setToolDiametr(toolDiametr);
        champherMill.setLength(length);
        champherMill.setFluteLength(fluteLength);
        champherMill.setFluteNumber(fluteNumber);
        champherMill.toolTapperAngle = toolTapperAngle;
        champherMill.chamferLength = chamferLength;
        return champherMill;
    }

    public double getToolTapperAngle() {
        return toolTapperAngle;
    }

    public void setToolTapperAngle(double toolTapperAngle) {
        this.toolTapperAngle = toolTapperAngle;
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
