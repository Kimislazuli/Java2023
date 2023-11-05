package edu.project2.solvers;

import edu.project2.Cell;
import edu.project2.Maze;
import edu.project2.Position;
import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearchSolver extends MazeSolver {
    private final List<Position> path = new ArrayList<>();

    private final static int[][] DIRECTIONS
        = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public DepthFirstSearchSolver(Maze maze, Position start, Position end) {
        super(maze, start, end);
    }

    @Override
    public void solve() {
        if (!dfs(start)) {
            throw new IllegalArgumentException("There's no path from start to end in this labyrinth.");
            }

        Cell[][] grid = maze.getGrid();

        for (Position position : path) {
            grid[position.row()][position.col()] = Cell.ROUTE;
        }
    }

    private boolean dfs(Position currentPosition) {
        if (visited.contains(currentPosition)
            || maze.getGrid()[currentPosition.row()][currentPosition.col()] == Cell.WALL) {
            return false;
        }

        path.add(currentPosition);
        visited.add(currentPosition);

        if (currentPosition.equals(end)) {
            return true;
        }

        for (int[] offset : DIRECTIONS) {
            Position newPosition = getNewPosition(currentPosition, offset);
            if (newPosition != null) {
                if (dfs(newPosition)) {
                    return true;
                }
            }
        }

        path.remove(currentPosition);
        return false;
    }

    private Position getNewPosition(Position currentPosition, int[] offset) {
        if (currentPosition.row() + offset[0] < maze.getHeight()
            && currentPosition.row() + offset[0] >= 0
            && currentPosition.col() + offset[1] < maze.getWidth()
            && currentPosition.col() + offset[1] >= 0) {
            return new Position(currentPosition.row() + offset[0], currentPosition.col() + offset[1]);
        }
        return null;
    }
}
