package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.Maze;
import edu.project2.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static edu.project2.Cell.PASS;

public abstract class MazeGenerator {
    protected final Random random = new Random();
    protected final Maze maze;
    protected final List<Position> cellsToCheck = new ArrayList<>();

    public MazeGenerator(Maze maze) {
        this.maze = maze;
    }

    public abstract void generate();

    protected void addNewCellsToCheck(Position currentPosition, int[][] directions) {
        for (int[] offset : directions) {
            Position newPosition = maze.getNewPosition(currentPosition, offset);
            if (newPosition != null) {
                cellsToCheck.add(newPosition);
            }
        }
    }

    protected void makePassage(Position currentPosition, Position newPosition) {
        Cell[][] grid = maze.getGrid();
        if (currentPosition.row() < newPosition.row()) {
            grid[currentPosition.row() + 1][currentPosition.col()] = PASS;
            grid[currentPosition.row() + 2][currentPosition.col()] = PASS;
        }

        if (currentPosition.row() > newPosition.row()) {
            grid[currentPosition.row() - 1][currentPosition.col()] = PASS;
            grid[currentPosition.row() - 2][currentPosition.col()] = PASS;
        }

        if (currentPosition.col() < newPosition.col()) {
            grid[currentPosition.row()][currentPosition.col() + 1] = PASS;
            grid[currentPosition.row()][currentPosition.col() + 2] = PASS;
        }

        if (currentPosition.col() > newPosition.col()) {
            grid[currentPosition.row()][currentPosition.col() - 1] = PASS;
            grid[currentPosition.row()][currentPosition.col() - 2] = PASS;
        }
    }
}
