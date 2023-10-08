package edu.hw1;

import java.awt.Point;

public final class Task8 {
    private static final int BOARD_SIZE = 8;
    private static final Point[] POSSIBLE_MOVES = new Point[] {
        new Point(-2, -1),
        new Point(-1, -2),
        new Point(1, -2),
        new Point(2, -1),
        new Point(2, 1),
        new Point(1, 2),
        new Point(-1, 2),
        new Point(-2, 1)
    };

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                for (Point target : POSSIBLE_MOVES) {
                    int targetXPosition = x + target.x;
                    int targetYPosition = y + target.y;

                    if (checkBorders(targetXPosition, targetYPosition)) {
                        if (board[x][y] * board[targetXPosition][targetYPosition] == 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean checkBorders(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }
}
