package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.Maze;
import edu.project2.Position;
import static edu.project2.Cell.PASS;
import static edu.project2.Cell.WALL;

public class PrimRandomizedGenerator extends MazeGenerator {
    private final static int[][] DIRECTIONS
        = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public PrimRandomizedGenerator(Maze maze) {
        super(maze);
    }

    @Override
    public void generate() {
        Cell[][] grid = maze.getGrid();
        Position startPosition = new Position(random.nextInt(maze.getHeight()), random.nextInt(maze.getWidth()));
        grid[startPosition.row()][startPosition.col()] = Cell.PASS;
        addNewCellsToCheck(startPosition, DIRECTIONS);
        while (!cellsToCheck.isEmpty()) {
            int randIndex = random.nextInt(cellsToCheck.size());
            Position currentPosition = cellsToCheck.get(randIndex);
            cellsToCheck.remove(randIndex);
            makeNewPassage(grid, currentPosition);
        }
    }

    private void makeNewPassage(Cell[][] grid, Position currentPosition) {
        if (currentPosition.col() + 1 < maze.getWidth() && currentPosition.col() - 1 >= 0) {
            if (grid[currentPosition.row()][currentPosition.col() + 1]
                != grid[currentPosition.row()][currentPosition.col() - 1]) {
                grid[currentPosition.row()][currentPosition.col()] = PASS;
                if (grid[currentPosition.row()][currentPosition.col() + 1] == WALL) {
                    grid[currentPosition.row()][currentPosition.col() + 1] = PASS;
                    addNewCellsToCheck(new Position(currentPosition.row(), currentPosition.col() + 1), DIRECTIONS);
                } else {
                    grid[currentPosition.row()][currentPosition.col() - 1] = PASS;
                    addNewCellsToCheck(new Position(currentPosition.row(), currentPosition.col() - 1), DIRECTIONS);
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
                    addNewCellsToCheck(new Position(currentPosition.row() + 1, currentPosition.col()), DIRECTIONS);
                } else {
                    grid[currentPosition.row() - 1][currentPosition.col()] = PASS;
                    addNewCellsToCheck(new Position(currentPosition.row() - 1, currentPosition.col()), DIRECTIONS);
                }
            }
        }
    }
}
