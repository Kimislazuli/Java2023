package edu.project2.generators;

import edu.project2.Maze;
import edu.project2.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
}
