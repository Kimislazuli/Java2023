package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MazeSolver {
    private final Maze maze;
    private final Position start;
    private final Position end;
    private final Deque<Position> cellsQueue = new ArrayDeque<>();
    private final List<Position> visited = new ArrayList<>();
    private final Map<Position, Position> parents = new HashMap<>();

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

    public void breadthFirstSearch() {
        cellsQueue.push(start);
        parents.put(start, null);

        while (!cellsQueue.isEmpty()) {
            Position currentPosition = cellsQueue.poll();
            visited.add(currentPosition);
            addNeighboursBFS(currentPosition);
        }

        Position currentPosition = end;
        Cell[][] grid = maze.getGrid();
        grid[end.row()][end.col()] = Cell.ROUTE;

        if (!parents.containsKey(end)) {
            throw new IllegalArgumentException("There's no path from start to end in this labyrinth.");
        }

        while (currentPosition != start) {
            visited.add(currentPosition);
            currentPosition = parents.get(currentPosition);
            grid[currentPosition.row()][currentPosition.col()] = Cell.ROUTE;
        }
    }

    private void addNeighboursBFS(Position currentPosition) {
        Cell[][] grid = maze.getGrid();
        int column = currentPosition.col();
        int row = currentPosition.row();

        if (column + 1 < maze.getWidth()) {
            if (grid[row][column + 1] == Cell.PASS) {
                tryToAddPosition(new Position(row, column + 1), currentPosition);
            }
        }

        if (column - 1 >= 0) {
            if (grid[row][column - 1] == Cell.PASS) {
                tryToAddPosition(new Position(row, column - 1), currentPosition);
            }
        }

        if (row + 1 < maze.getHeight()) {
            if (grid[row + 1][column] == Cell.PASS) {
                tryToAddPosition(new Position(row + 1, column), currentPosition);
            }
        }

        if (row - 1 >= 0) {
            if (grid[row - 1][column] == Cell.PASS) {
                tryToAddPosition(new Position(row - 1, column), currentPosition);
            }
        }
    }

    private void tryToAddPosition(Position newPosition, Position currentPosition) {
        if (!visited.contains(newPosition) && !cellsQueue.contains(newPosition)) {
            parents.put(newPosition, currentPosition);
            cellsQueue.add(newPosition);
        }
    }
}
