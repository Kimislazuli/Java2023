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
        int row = currentPosition.row();
        int col = currentPosition.col();
        if (grid[row][col] == WALL && col + 1 < maze.getWidth() && col - 1 >= 0) {
            if (grid[row][col + 1] != grid[row][col - 1]) {
                grid[row][col] = PASS;
                if (grid[row][col + 1] == WALL) {
                    grid[row][col + 1] = PASS;
                    addNewCellsToCheck(new Position(row, col + 1), DIRECTIONS);
                } else {
                    grid[row][col - 1] = PASS;
                    addNewCellsToCheck(new Position(row, col - 1), DIRECTIONS);
                }
                return;
            }
        }
        if (grid[row][col] == WALL && row - 1 >= 0 && row + 1 < maze.getHeight()) {
            if (grid[row + 1][col] != grid[row - 1][col]) {
                grid[row][col] = PASS;
                if (grid[row + 1][col] == WALL) {
                    grid[row + 1][col] = PASS;
                    addNewCellsToCheck(new Position(row + 1, col), DIRECTIONS);
                } else {
                    grid[row - 1][col] = PASS;
                    addNewCellsToCheck(new Position(row - 1, col), DIRECTIONS);
                }
            }
        }
    }
}
