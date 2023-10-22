package edu.project1;

public final class HangmanGame {
    private HangmanGame() {
    }

    public static void run() {
        GameView view = new GameView();
        view.startGame();
        while (!view.isGameEndFlag()) {
            view.updateView();
        }
    }
}
