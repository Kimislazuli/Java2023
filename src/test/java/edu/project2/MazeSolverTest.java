package edu.project2;

import edu.project2.solvers.BreadthFirstSearchSolver;
import edu.project2.solvers.DepthFirstSearchSolver;
import edu.project2.solvers.MazeSolver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static edu.project2.Cell.PASS;
import static edu.project2.Cell.ROUTE;
import static edu.project2.Cell.WALL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MazeSolverTest {
    @Test
    @DisplayName("Поиск в ширину может найти путь")
    void bfsCanFindPath() {
        Maze maze = new Maze(5, 5);
        Cell[][] grid = maze.getGrid();
        grid[0] = new Cell[] {PASS, PASS, PASS, WALL, PASS};
        grid[1] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
        grid[2] = new Cell[] {PASS, PASS, PASS, PASS, PASS};
        grid[3] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
        grid[4] = new Cell[] {PASS, PASS, PASS, WALL, PASS};

        MazeSolver mazeSolver = new BreadthFirstSearchSolver(maze, new Position(0, 0), new Position(0, 4));
        mazeSolver.solve();

        Cell[][] expectedResult = new Cell[5][5];
        expectedResult[0] = new Cell[] {ROUTE, ROUTE, ROUTE, WALL, ROUTE};
        expectedResult[1] = new Cell[] {WALL, WALL, ROUTE, WALL, ROUTE};
        expectedResult[2] = new Cell[] {PASS, PASS, ROUTE, ROUTE, ROUTE};
        expectedResult[3] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
        expectedResult[4] = new Cell[] {PASS, PASS, PASS, WALL, PASS};

        assertThat(maze.getGrid()).isDeepEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Поиск в ширину не может найти путь")
    void bfsCannotFindPath() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Maze maze = new Maze(5, 5);
                Cell[][] grid = maze.getGrid();
                grid[0] = new Cell[] {PASS, PASS, PASS, WALL, PASS};
                grid[1] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
                grid[2] = new Cell[] {PASS, PASS, PASS, WALL, PASS};
                grid[3] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
                grid[4] = new Cell[] {PASS, PASS, PASS, WALL, PASS};

                MazeSolver mazeSolver = new BreadthFirstSearchSolver(maze, new Position(0, 0), new Position(0, 4));
                mazeSolver.solve();
            }
        );
    }

    @Test
    @DisplayName("Поиск в глубину может найти путь")
    void dfsCanFindPath() {
        Maze maze = new Maze(5, 5);
        Cell[][] grid = maze.getGrid();
        grid[0] = new Cell[] {PASS, PASS, PASS, WALL, PASS};
        grid[1] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
        grid[2] = new Cell[] {PASS, PASS, PASS, PASS, PASS};
        grid[3] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
        grid[4] = new Cell[] {PASS, PASS, PASS, WALL, PASS};

        MazeSolver mazeSolver = new DepthFirstSearchSolver(maze, new Position(0, 0), new Position(0, 4));
        mazeSolver.solve();

        Cell[][] expectedResult = new Cell[5][5];
        expectedResult[0] = new Cell[] {ROUTE, ROUTE, ROUTE, WALL, ROUTE};
        expectedResult[1] = new Cell[] {WALL, WALL, ROUTE, WALL, ROUTE};
        expectedResult[2] = new Cell[] {PASS, PASS, ROUTE, ROUTE, ROUTE};
        expectedResult[3] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
        expectedResult[4] = new Cell[] {PASS, PASS, PASS, WALL, PASS};

        assertThat(maze.getGrid()).isDeepEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Поиск в глубину не может найти путь")
    void dfsCannotFindPath() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Maze maze = new Maze(5, 5);
                Cell[][] grid = maze.getGrid();
                grid[0] = new Cell[] {PASS, PASS, PASS, WALL, PASS};
                grid[1] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
                grid[2] = new Cell[] {PASS, PASS, PASS, WALL, PASS};
                grid[3] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
                grid[4] = new Cell[] {PASS, PASS, PASS, WALL, PASS};

                MazeSolver mazeSolver = new DepthFirstSearchSolver(maze, new Position(0, 0), new Position(0, 4));
                mazeSolver.solve();
            }
        );
    }

    @Test
    @DisplayName("Отрисовка лабиринта верная")
    void correctRendering() {
        Maze maze = new Maze(5, 5);
        Cell[][] grid = maze.getGrid();
        grid[0] = new Cell[] {ROUTE, ROUTE, ROUTE, WALL, ROUTE};
        grid[1] = new Cell[] {WALL, WALL, ROUTE, WALL, ROUTE};
        grid[2] = new Cell[] {PASS, PASS, ROUTE, ROUTE, ROUTE};
        grid[3] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
        grid[4] = new Cell[] {PASS, PASS, PASS, WALL, PASS};

        List<String> renderedMaze = PrettyMazeRenderer.processToOutput(maze);

        List<String> expectedResult = new ArrayList<String>();
        expectedResult.add("\033[47m   ".repeat(7) + "\u001B[0m   ");
        expectedResult.add(
            "\033[47m   \u001B[0m\u001B[35m • \u001B[0m\u001B[35m • \u001B[0m\u001B[35m • \u001B[40m   \u001B[0m\u001B[35m • \033[47m   \u001B[0m   ");
        expectedResult.add(
            "\033[47m   \u001B[40m   \u001B[40m   \u001B[0m\u001B[35m • \u001B[40m   \u001B[0m\u001B[35m • \033[47m   \u001B[0m   ");
        expectedResult.add(
            "\033[47m   \u001B[0m   \u001B[0m   \u001B[0m\u001B[35m • \u001B[0m\u001B[35m • \u001B[0m\u001B[35m • \033[47m   \u001B[0m   ");
        expectedResult.add(
            "\033[47m   \u001B[40m   \u001B[40m   \u001B[0m   \u001B[40m   \u001B[0m   \033[47m   \u001B[0m   ");
        expectedResult.add(
            "\033[47m   \u001B[0m   \u001B[0m   \u001B[0m   \u001B[40m   \u001B[0m   \033[47m   \u001B[0m   ");
        expectedResult.add("\033[47m   ".repeat(7) + "\u001B[0m   ");

        assertThat(renderedMaze).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Маленькая ширина")
    void smallWidth() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                new Maze(10, 2);
            }
        );
    }

    @Test
    @DisplayName("Маленькая высота")
    void smallHeight() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                new Maze(1, 20);
            }
        );
    }

    @Test
    @DisplayName("Финиш в стене")
    void endPointAtWall() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Maze maze = new Maze(5, 5);
                Cell[][] grid = maze.getGrid();
                grid[0] = new Cell[] {PASS, PASS, PASS, WALL, PASS};
                grid[1] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
                grid[2] = new Cell[] {PASS, PASS, PASS, PASS, PASS};
                grid[3] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
                grid[4] = new Cell[] {PASS, PASS, PASS, WALL, PASS};

                new BreadthFirstSearchSolver(maze, new Position(0, 0), new Position(0, 3));
            }
        );
    }

    @Test
    @DisplayName("Старт в стене")
    void startPointAtWall() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Maze maze = new Maze(5, 5);
                Cell[][] grid = maze.getGrid();
                grid[0] = new Cell[] {PASS, PASS, PASS, WALL, PASS};
                grid[1] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
                grid[2] = new Cell[] {PASS, PASS, PASS, PASS, PASS};
                grid[3] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
                grid[4] = new Cell[] {PASS, PASS, PASS, WALL, PASS};

                new BreadthFirstSearchSolver(maze, new Position(1, 0), new Position(0, 4));
            }
        );
    }

    @Test
    @DisplayName("Финиш не в сетке")
    void endPointOutOfGrid() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Maze maze = new Maze(5, 5);
                Cell[][] grid = maze.getGrid();
                grid[0] = new Cell[] {PASS, PASS, PASS, WALL, PASS};
                grid[1] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
                grid[2] = new Cell[] {PASS, PASS, PASS, PASS, PASS};
                grid[3] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
                grid[4] = new Cell[] {PASS, PASS, PASS, WALL, PASS};

                new BreadthFirstSearchSolver(maze, new Position(0, 0), new Position(0, 8));
            }
        );
    }

    @Test
    @DisplayName("Старт не в сетке")
    void startPointOutOfGrid() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Maze maze = new Maze(5, 5);
                Cell[][] grid = maze.getGrid();
                grid[0] = new Cell[] {PASS, PASS, PASS, WALL, PASS};
                grid[1] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
                grid[2] = new Cell[] {PASS, PASS, PASS, PASS, PASS};
                grid[3] = new Cell[] {WALL, WALL, PASS, WALL, PASS};
                grid[4] = new Cell[] {PASS, PASS, PASS, WALL, PASS};

                new BreadthFirstSearchSolver(maze, new Position(6, 0), new Position(0, 4));
            }
        );
    }
}
