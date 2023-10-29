package edu.project1;

import java.util.Random;

public class GameModel {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private String word;
    private static final int MISTAKES_ALLOWED = 5;
    private int mistakesDone = 0;
    private char[] guessedPart;
    protected final String[] dictionary;
    private char[] unusedLetters = ALPHABET.toCharArray();

    public String getWordMask() {
        return parseCharArray(guessedPart);
    }

    public GameModel(String[] dictionary) {
        this.dictionary = dictionary;
        initializeGame();
    }

    private void restartGame() {
        initializeGame();
    }

    private void initializeGame() {
        word = randomizeWord();
        if (word.length() <= 1) {
            throw new IllegalArgumentException(InputReaction.WORD_TOO_SHORT.getText());
        }
        guessedPart = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            guessedPart[i] = '*';
        }
        unusedLetters = ALPHABET.toCharArray();
        mistakesDone = 0;
    }

    private String randomizeWord() {
        Random random = new Random();
        return dictionary[random.nextInt(dictionary.length)];
    }

    public String[] processInput(String userInput) {
        if (userInput.length() != 1 || !userInput.matches("[a-zA-Z]")) {
            return processOtherInput(userInput);
        }
        return processLetterInput(userInput.toLowerCase().charAt(0));
    }

    private String[] processLetterInput(char userInput) {
        int index = word.indexOf(userInput);
        if (!checkIfArrayContainsChar(unusedLetters, userInput)) {
            return new String[] {
                InputReaction.USED_LETTER.getText(),
                parseCharArray(unusedLetters)};
        }
        if (index != -1) {
            return processGuessedLetter(index, userInput);
        }
        return processMiss(userInput);
    }

    private String[] processMiss(char userInput) {
        if (mistakesDone < MISTAKES_ALLOWED) {
            unusedLetters[new String(unusedLetters).indexOf(userInput)] = '_';
            mistakesDone++;
            return new String[] {
                InputReaction.WRONG_LETTER.getText(),
                InputReaction.MISTAKES_LEFT.getText() + mistakesDone + "/" + MISTAKES_ALLOWED};
        }
        String oldWord = word;
        restartGame();
        return new String[] {
            InputReaction.DEFEAT.getText(),
            InputReaction.THE_WORD_WAS.getText() + oldWord,
            InputReaction.START_NEW_GAME.getText(),
            InputReaction.HELP.getText(),
            parseCharArray(guessedPart)};
    }

    private String[] processGuessedLetter(int index, char userInput) {
        guessedPart[index] = userInput;
        unusedLetters[new String(unusedLetters).indexOf(userInput)] = '_';
        int newIndex = index + 1;
        while (newIndex < word.length()) {
            if (word.indexOf(userInput, newIndex) != -1) {
                guessedPart[word.indexOf(userInput, newIndex)] = userInput;
            }
            newIndex++;
        }
        if (checkIfArrayContainsChar(guessedPart, '*')) {
            return new String[] {
                InputReaction.LETTER_GUESSED.getText(),
                InputReaction.GUESSED_PART_OF_WORD.getText(),
                parseCharArray(guessedPart),
                InputReaction.INPUT_NEXT_LETTER.getText()};
        }
        String oldWord = word;
        restartGame();
        return new String[] {
            InputReaction.WIN.getText(),
            InputReaction.THE_WORD_WAS.getText() + oldWord,
            InputReaction.START_NEW_GAME.getText(),
            InputReaction.HELP.getText(),
            parseCharArray(guessedPart)};
    }

    private boolean checkIfArrayContainsChar(char[] charArray, char character) {
        for (char c : charArray) {
            if (c == character) {
                return true;
            }
        }
        return false;
    }

    private String[] processOtherInput(String userInput) {
        if (userInput.equalsIgnoreCase("give up")) {
            String oldWord = word;
            restartGame();
            return new String[] {
                InputReaction.GAVE_UP.getText(),
                InputReaction.THE_WORD_WAS.getText() + oldWord,
                InputReaction.START_NEW_GAME.getText(),
                InputReaction.HELP.getText(),
                parseCharArray(guessedPart)};
        } else if (userInput.equalsIgnoreCase("quit")) {
            return new String[] {InputReaction.CLOSE_THE_GAME.getText()};
        }
        return new String[] {InputReaction.INVALID_INPUT.getText()};
    }

    private String parseCharArray(char[] chars) {
        StringBuilder builder = new StringBuilder();
        return builder.append(chars).toString();
    }
}
