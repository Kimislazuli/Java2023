package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.Maze;
import edu.project2.Position;
import java.util.ArrayList;
import java.util.List;
import static edu.project2.Cell.PASS;

public class RecursiveBacktrackerGenerator extends MazeGenerator {
    private List<Position> visited = new ArrayList<>();
    private final static int[][] DIRECTIONS
        = new int[][] {{0, 2}, {2, 0}, {0, -2}, {-2, 0}};

    public RecursiveBacktrackerGenerator(Maze maze) {
        super(maze);
    }

    @Override
    public void generate() {
        Position currentPosition = new Position(random.nextInt(maze.getHeight()), random.nextInt(maze.getWidth()));
        Cell[][] grid = maze.getGrid();
        grid[currentPosition.row()][currentPosition.col()] = PASS;
        recursiveBacktracker(currentPosition);
    }

    private void recursiveBacktracker(Position currentPosition) {
        visited.add(currentPosition);
        List<Position> unvisitedNeighbours = findUnvisitedNeighbours(currentPosition);
        while (!unvisitedNeighbours.isEmpty()) {
            Position newPosition = unvisitedNeighbours.get(random.nextInt(unvisitedNeighbours.size()));
            unvisitedNeighbours.remove(newPosition);
            if (!visited.contains(newPosition)) {
                makePassage(currentPosition, newPosition);
                recursiveBacktracker(newPosition);
            }
        }
    }

    private List<Position> findUnvisitedNeighbours(Position currentPosition) {
        List<Position> unvisitedNeighbours = new ArrayList<>();

        for (int[] offset : DIRECTIONS) {
            Position newPosition = maze.getNewPosition(currentPosition, offset);
            if (newPosition != null && !visited.contains(newPosition)) {
                unvisitedNeighbours.add(newPosition);
            }
        }

        return unvisitedNeighbours;
    }

//    protected void makePassage(Position currentPosition, Position newPosition) {
//        Cell[][] grid = maze.getGrid();
//        if (currentPosition.row() < newPosition.row()) {
//            grid[currentPosition.row() + 1][currentPosition.col()] = PASS;
//            grid[currentPosition.row() + 2][currentPosition.col()] = PASS;
//        }
//
//        if (currentPosition.row() > newPosition.row()) {
//            grid[currentPosition.row() - 1][currentPosition.col()] = PASS;
//            grid[currentPosition.row() - 2][currentPosition.col()] = PASS;
//        }
//
//        if (currentPosition.col() < newPosition.col()) {
//            grid[currentPosition.row()][currentPosition.col() + 1] = PASS;
//            grid[currentPosition.row()][currentPosition.col() + 2] = PASS;
//        }
//
//        if (currentPosition.col() > newPosition.col()) {
//            grid[currentPosition.row()][currentPosition.col() - 1] = PASS;
//            grid[currentPosition.row()][currentPosition.col() - 2] = PASS;
//        }
//    }
}
