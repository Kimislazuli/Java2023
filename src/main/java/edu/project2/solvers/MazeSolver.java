package edu.project2.solvers;

import edu.project2.Cell;
import edu.project2.Maze;
import edu.project2.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class MazeSolver {
    protected final Maze maze;
    protected final Position start;
    protected final Position end;
    protected final List<Position> visited = new ArrayList<>();

    public MazeSolver(Maze maze, Position start, Position end) {
        this.maze = maze;
        Cell[][] grid = maze.getGrid();
        if (isPositionInTheGrid(start)) {
            if (grid[start.row()][start.col()] == Cell.PASS) {
                this.start = start;
            } else {
                throw new IllegalArgumentException("Start position is wall");
            }
        } else {
            throw new IllegalArgumentException("Start position is out of grid");
        }

        if (isPositionInTheGrid(end)) {
            if (grid[end.row()][end.col()] == Cell.PASS) {
                this.end = end;
            } else {
                throw new IllegalArgumentException("End position is wall");
            }
        } else {
            throw new IllegalArgumentException("End position is out of grid");
        }
    }

    private boolean isPositionInTheGrid(Position position) {
        return position.col() < maze.getWidth() && position.col() >= 0
            && position.row() < maze.getHeight() && position.row() >= 0;
    }

    public abstract void solve();
}
