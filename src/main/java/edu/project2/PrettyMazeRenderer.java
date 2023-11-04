package edu.project2;

import java.util.ArrayList;
import java.util.List;

public final class PrettyMazeRenderer {
    private static final String WALL = "\u001B[40m   ";
    private static final String OUTER_WALL = "\033[47m   ";
    private static final String PASSAGE = "\u001B[0m   ";
    private static final String ROUTE_POINT = "\u001B[0m\u001B[35m • ";

    private PrettyMazeRenderer() {}

    @SuppressWarnings("checkstyle:MissingSwitchDefault") public static List<String> processToOutput(Maze maze) {
        List<String> output = new ArrayList<>();
        Cell[][] grid = maze.getGrid();
        output.add(OUTER_WALL.repeat(maze.getWidth() + 2) + PASSAGE);
        for (var row : grid) {
            StringBuilder currentRow = new StringBuilder();
            currentRow.append(OUTER_WALL);
            for (var element : row) {
                switch (element) {
                    case PASS -> currentRow.append(PASSAGE);
                    case WALL -> currentRow.append(WALL);
                    case ROUTE -> currentRow.append(ROUTE_POINT);
                }
            }
            currentRow.append(OUTER_WALL);
            currentRow.append(PASSAGE);
            output.add(currentRow.toString());
        }
        output.add(OUTER_WALL.repeat(maze.getWidth() + 2) + PASSAGE);
        return output;
    }
}
