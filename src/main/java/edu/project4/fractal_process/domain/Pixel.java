package edu.project4.fractal_process.domain;

import java.awt.Color;

public class Pixel {
    private Color color = new Color(0, 0, 0);
    private int hitCount = 0;
    private double normal = 0;

    public void newHit() {
        hitCount++;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getHitCount() {
        return hitCount;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }
}
