package stp.demonick.basecncprog.model.tools;

import java.util.StringJoiner;

public class TCutterMillTool extends Tool {
    private double shankDiameter;
    private double lowerCornerRadius;
    private double upperCornerRadius;

    public double getShankDiameter() {
        return shankDiameter;
    }

    public void setShankDiameter(double shankDiameter) {
        this.shankDiameter = shankDiameter;
    }

    public double getLowerCornerRadius() {
        return lowerCornerRadius;
    }

    public void setLowerCornerRadius(double lowerCornerRadius) {
        this.lowerCornerRadius = lowerCornerRadius;
    }

    public double getUpperCornerRadius() {
        return upperCornerRadius;
    }

    public void setUpperCornerRadius(double upperCornerRadius) {
        this.upperCornerRadius = upperCornerRadius;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TCutterMillTool.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("toolNumber=" + getToolNumber())
                .add("toolName='" + getToolName() + "'")
                .add("toolDiametr=" + getToolDiametr())
                .add("length=" + getLength())
                .add("fluteLength=" + getFluteLength())
                .add("fluteNumber=" + getFluteNumber())
                .add("shankDiameter=" + shankDiameter)
                .add("lowerCornerRadius=" + lowerCornerRadius)
                .add("upperCornerRadius=" + upperCornerRadius)
                .toString();
    }
}
