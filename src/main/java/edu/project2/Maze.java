package edu.project2;

import java.util.Arrays;
import static edu.project2.Cell.WALL;

public class Maze {
    private static final int MIN_DIMENSION = 5;
    private static final String DIMENSION_ERROR_MESSAGE = "Each side of labyrinth should be at least 5.";
    private final int width;
    private final int height;
    private final Cell[][] grid;

    public Maze(int width, int height) {
        if (width >= MIN_DIMENSION) {
            this.width = width;
        } else {
            throw new IllegalArgumentException(DIMENSION_ERROR_MESSAGE);
        }

        if (height >= MIN_DIMENSION) {
            this.height = height;
        } else {
            throw new IllegalArgumentException(DIMENSION_ERROR_MESSAGE);
        }
        grid = new Cell[height][width];
        Arrays.stream(grid).forEach(a -> Arrays.fill(a, WALL));
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Position getNewPosition(Position currentPosition, int[] offset) {
        if (currentPosition.row() + offset[0] < height
            && currentPosition.row() + offset[0] >= 0
            && currentPosition.col() + offset[1] < width
            && currentPosition.col() + offset[1] >= 0) {
            return new Position(currentPosition.row() + offset[0], currentPosition.col() + offset[1]);
        }
        return null;
    }
}
