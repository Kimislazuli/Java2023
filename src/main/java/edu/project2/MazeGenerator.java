package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static edu.project2.Cell.PASS;
import static edu.project2.Cell.WALL;

public class MazeGenerator {
    private final Random random = new Random();
    private final Maze maze;
    private final List<Position> cellsToCheck = new ArrayList<>();

    public MazeGenerator(Maze maze) {
        this.maze = maze;
    }

    public void primSimplifiedAlgorithm() {
        Cell[][] grid = maze.getGrid();
        Position startPosition = new Position(random.nextInt(maze.getHeight()), random.nextInt(maze.getWidth()));
        grid[startPosition.row()][startPosition.col()] = Cell.PASS;
        addNewCellsToCheck(startPosition);
        while (!cellsToCheck.isEmpty()) {
            int randIndex = random.nextInt(cellsToCheck.size());
            Position currentPosition = cellsToCheck.get(randIndex);
            cellsToCheck.remove(randIndex);
            makeNewPassageForPrim(grid, currentPosition);
        }
    }

    private void addNewCellsToCheck(Position currentPosition) {
        Cell[][] grid = maze.getGrid();
        int currentRow = currentPosition.row();
        int currentCol = currentPosition.col();

        if (currentCol + 1 < maze.getWidth()) {
            if (grid[currentRow][currentCol + 1] == WALL
                && !cellsToCheck.contains(new Position(currentRow, currentCol + 1))) {
                cellsToCheck.add(new Position(currentRow, currentCol + 1));
            }
        }

        if (currentCol - 1 >= 0) {
            if (grid[currentRow][currentCol - 1] == WALL
                && !cellsToCheck.contains(new Position(currentRow, currentCol - 1))) {
                cellsToCheck.add(new Position(currentRow, currentCol - 1));
            }
        }

        if (currentRow + 1 < maze.getHeight()) {
            if (grid[currentRow + 1][currentCol] == WALL
                && !cellsToCheck.contains(new Position(currentRow + 1, currentCol))) {
                cellsToCheck.add(new Position(currentRow + 1, currentCol));
            }
        }

        if (currentRow - 1 >= 0) {
            if (grid[currentRow - 1][currentCol] == WALL
                && !cellsToCheck.contains(new Position(currentRow - 1, currentCol))) {
                cellsToCheck.add(new Position(currentRow - 1, currentCol));
            }
        }
    }

    private void makeNewPassageForPrim(Cell[][] grid, Position currentPosition) {
        if (currentPosition.col() + 1 < maze.getWidth() && currentPosition.col() - 1 >= 0) {
            if (grid[currentPosition.row()][currentPosition.col() + 1]
                != grid[currentPosition.row()][currentPosition.col() - 1]) {
                grid[currentPosition.row()][currentPosition.col()] = PASS;
                if (grid[currentPosition.row()][currentPosition.col() + 1] == WALL) {
                    grid[currentPosition.row()][currentPosition.col() + 1] = PASS;
                    addNewCellsToCheck(new Position(currentPosition.row(), currentPosition.col() + 1));
                } else {
                    grid[currentPosition.row()][currentPosition.col() - 1] = PASS;
                    addNewCellsToCheck(new Position(currentPosition.row(), currentPosition.col() - 1));
                }
                return;
            }
        }
        if (currentPosition.row() - 1 >= 0 && currentPosition.row() + 1 < maze.getHeight()) {
            if (grid[currentPosition.row() + 1][currentPosition.col()]
                != grid[currentPosition.row() - 1][currentPosition.col()]) {
                grid[currentPosition.row()][currentPosition.col()] = PASS;
                if (grid[currentPosition.row() + 1][currentPosition.col()] == WALL) {
                    grid[currentPosition.row() + 1][currentPosition.col()] = PASS;
                    addNewCellsToCheck(new Position(currentPosition.row() + 1, currentPosition.col()));
                } else {
                    grid[currentPosition.row() - 1][currentPosition.col()] = PASS;
                    addNewCellsToCheck(new Position(currentPosition.row() - 1, currentPosition.col()));
                }
            }
        }
    }
}
