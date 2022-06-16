package stp.demonick.basecncprog.model.tools;

import javax.persistence.Entity;
import java.util.StringJoiner;
@Entity
public class DrillingTool extends Tool {
    private double toolPointAngle;

    public double getToolPointAngle() {
        return toolPointAngle;
    }

    public void setToolPointAngle(double toolPointAngle) {
        this.toolPointAngle = Math.toDegrees(toolPointAngle);
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
