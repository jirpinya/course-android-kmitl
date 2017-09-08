package kmitl.lab03.jirapinya58070014.simplemydot.model;

import java.io.Serializable;

/**
 * Created by student on 9/8/2017 AD.
 */

public class DotSerializable implements Serializable{
    private int centerX;
    private int centerY;
    private int radius;
    private int color;

    @Override
    public String toString() {
        return "DotSerializable{" +
                "centerX=" + centerX +
                ", centerY=" + centerY +
                ", radius=" + radius +
                ", color=" + color +
                '}';
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
