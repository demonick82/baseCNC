package stp.demonick.basecncprog.model.tools;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.StringJoiner;

@Entity
@Table(name = "milling_tools")
public class MillingTool extends Tool {
    private double cornerRadius;

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
