package edu.project1;

public enum InputReaction {
    WORD_TOO_SHORT("The word for game should have at least 2 letters. "
        + "Please remove wrong words from game configuration."),
    LETTER_GUESSED("Right! Keep going!"),
    WRONG_LETTER("No, you missed... Try again!"),
    INPUT_NEXT_LETTER("Please input next letter."),
    GUESSED_PART_OF_WORD("Guessed part of word is:"),
    USED_LETTER("You've already used this letter. Choose another one from this letters:"),
    WIN("Congratulations! You've won."),
    DEFEAT("Oh, sorry... You've lost. Better luck next time."),
    MISTAKES_LEFT("Mistakes: "),
    START_NEW_GAME("New game has started."),
    INVALID_INPUT("Something went wrong. Check your input and try again."),
    HELP("You can enter the letter to guess the word, \"give up\" "
        + "to end current session and \"quit\" to close the game."),
    CLOSE_THE_GAME("Thank you for playing the game. Good bye."),
    GAVE_UP("You tried really hard"),
    THE_WORD_WAS("The word was: "),
    ENTER_THE_LETTER("Please enter the letter.");

    private final String text;

    InputReaction(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
