package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameView {

    private final GameViewModel viewModel = new GameViewModel();
    private static final Logger LOGGER = LogManager.getLogger(GameView.class);
    private final Scanner scanner = new Scanner(System.in);
    private boolean gameEndFlag = false;

    public void updateViewModel(String userInput) {
        viewModel.setData(userInput);
    }

    public void updateView() {
        String userInput = scanner.nextLine();
        updateViewModel(userInput);
        for (String str : viewModel.getUpdateFromViewModel()) {
            gameEndFlag = str.equalsIgnoreCase(InputReaction.CLOSE_THE_GAME.getText());
            LOGGER.info(str);
        }
    }

    public void startGame() {
        LOGGER.info(InputReaction.START_NEW_GAME.getText());
        LOGGER.info(InputReaction.HELP.getText());
        LOGGER.info(viewModel.getWordMask());
    }

    public boolean isGameEndFlag() {
        return gameEndFlag;
    }

}
