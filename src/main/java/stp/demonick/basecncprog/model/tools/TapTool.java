package stp.demonick.basecncprog.model.tools;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.StringJoiner;

@Entity
@Table(name = "tap_tools")
public class TapTool extends Tool {
    private double toolPitch;

    public double getToolPitch() {
        return toolPitch;
    }

    public void setToolPitch(double toolPitch) {
        this.toolPitch = toolPitch;
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
                .add("pitch=" + toolPitch)
                .toString();
    }
}
