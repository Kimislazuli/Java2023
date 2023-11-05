package edu.project2;

import edu.project2.solvers.BreadthFirstSearchSolver;
import edu.project2.solvers.DepthFirstSearchSolver;
import edu.project2.solvers.MazeSolver;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("RegexpSinglelineJava")
public final class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    private Main() {
    }

    public static void main(String[] args) {
        System.out.println("Hello! This is maze generator.");
        System.out.println("Please enter dimension of maze:");
        System.out.print("width: ");
        int width = SCANNER.nextInt();
        System.out.print("height: ");
        int height = SCANNER.nextInt();

        Maze maze = new Maze(width, height);
        MazeGenerator mazeGenerator = new MazeGenerator(maze);
        mazeGenerator.primSimplifiedAlgorithm();
        System.out.println();

        List<String> result = PrettyMazeRenderer.processToOutput(maze);
        for (var row : result) {
            System.out.println(row);
        }

        System.out.println(
            "Enter start point (coordinates from 1 to dimension in order from left to right, from top to bottom):");
        System.out.print("start's row: ");
        int rowStart = SCANNER.nextInt();
        System.out.print("start's column: ");
        int colStart = SCANNER.nextInt();
        Position start = new Position(rowStart - 1, colStart - 1);

        System.out.println("Enter end point:");
        System.out.print("end's row: ");
        int rowEnd = SCANNER.nextInt();
        System.out.print("end's column: ");
        int colEnd = SCANNER.nextInt();
        Position end = new Position(rowEnd - 1, colEnd - 1);

        System.out.println("Also choose algorithm:");
        System.out.println("Print 1 for BFS");
        System.out.println("Print 2 for DFS");
        int algorithm = SCANNER.nextInt();

        MazeSolver mazeSolver;

        if (algorithm == 1) {
            mazeSolver = new BreadthFirstSearchSolver(maze, start, end);
        } else if (algorithm == 2) {
            mazeSolver = new DepthFirstSearchSolver(maze, start, end);
        } else {
            throw new IllegalArgumentException("No such algorithm.");
        }

        mazeSolver.solve();

        List<String> solveResult = PrettyMazeRenderer.processToOutput(maze);
        for (var row : solveResult) {
            System.out.println(row);
        }
    }
}
