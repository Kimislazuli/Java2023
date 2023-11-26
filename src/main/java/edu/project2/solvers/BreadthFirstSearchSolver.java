package edu.project2.solvers;

import edu.project2.Cell;
import edu.project2.Maze;
import edu.project2.Position;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class BreadthFirstSearchSolver extends MazeSolver {
    private final Deque<Position> cellsDeque = new ArrayDeque<>();
    private final Map<Position, Position> parents = new HashMap<>();

    public BreadthFirstSearchSolver(Maze maze, Position start, Position end) {
        super(maze, start, end);
    }

    public void solve() {
        cellsDeque.push(start);
        parents.put(start, null);

        while (!cellsDeque.isEmpty()) {
            Position currentPosition = cellsDeque.poll();
            if (currentPosition.equals(end)) {
                break;
            }
            visited.add(currentPosition);
            addNeighbours(currentPosition);
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

    private void addNeighbours(Position currentPosition) {
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
        if (!visited.contains(newPosition) && !cellsDeque.contains(newPosition)) {
            parents.put(newPosition, currentPosition);
            cellsDeque.add(newPosition);
        }
    }
}
