package stp.demonick.basecncprog.model.tools;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.StringJoiner;

@Entity
@Table(name = "thread_mill_tools")
public class ThreadMillTool extends Tool {
    private double toolPitch;


    public double getToolPitch() {
        return toolPitch;
    }

    public void setToolPitch(double toolPitch) {
        this.toolPitch = toolPitch;
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
                .add("pitch=" + toolPitch)
                .toString();
    }
}
