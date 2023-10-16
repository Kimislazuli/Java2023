package edu.hw2.Task2;

public class Rectangle {
    private final int height;
    private final int width;

    public Rectangle(int height, int width) {
        if (height < 0 ||  width < 0) {
            throw new IllegalArgumentException("Height and width should be non-negative");
        }
        this.height = height;
        this.width = width;
    }

    public Rectangle() {
        height = 0;
        width = 0;
    }

    public int area() {
        return height * width;
    }

    public Rectangle setWidth(int newWidth) {
        return new Rectangle(height, newWidth);
    }

    public Rectangle setHeight(int newHeight) {
        return new Rectangle(newHeight, width);
    }
}
