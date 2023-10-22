package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameModelTest {
    @Test
    @DisplayName("Слово из 1 символа")
    void tooShortWordException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                new GameModel(new String[] {"a"});
            });
    }

    @Test
    @DisplayName("Пустое слово")
    void emptyWordException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                new GameModel(new String[] {""});
            });
    }

    @Test
    @DisplayName("Правильная буква, встречающаяся 1 раз")
    void inputOneCorrectLetterUsedInWordOnce() {
        GameModel model = new GameModel(new String[] {"apple"});
        String[] actualResult = model.processInput("a");
        assertThat(actualResult).contains(
            InputReaction.INPUT_NEXT_LETTER.getText(),
            "a****");
    }

    @Test
    @DisplayName("Правильная буква, встречающаяся 2 раза")
    void inputOneCorrectLetterUsedInWordTwice() {
        GameModel model = new GameModel(new String[] {"apple"});
        String[] actualResult = model.processInput("p");
        assertThat(actualResult).contains(
            InputReaction.INPUT_NEXT_LETTER.getText(),
            "*pp**");
    }

    @Test
    @DisplayName("Повторный ввод буквы")
    void inputTheSameLetterAgain() {
        GameModel model = new GameModel(new String[] {"apple"});
        model.processInput("p");
        String[] actualResult = model.processInput("P");
        assertThat(actualResult).contains(
            InputReaction.USED_LETTER.getText());
    }

    @Test
    @DisplayName("Ввод неправильной буквы")
    void inputWrongLetter() {
        GameModel model = new GameModel(new String[] {"apple"});
        String[] actualResult = model.processInput("B");
        assertThat(actualResult).contains(
            InputReaction.WRONG_LETTER.getText());
    }

    @Test
    @DisplayName("Ввод неправильного символа")
    void inputOneWrongSymbol() {
        GameModel model = new GameModel(new String[] {"apple"});
        String[] actualResult = model.processInput(";'");
        assertThat(actualResult).contains(
            InputReaction.INVALID_INPUT.getText());
    }

    @Test
    @DisplayName("Ввод неправильного слова")
    void inputOneWrongWord() {
        GameModel model = new GameModel(new String[] {"apple"});
        String[] actualResult = model.processInput("gve up");
        assertThat(actualResult).contains(
            InputReaction.INVALID_INPUT.getText());
    }

    @Test
    @DisplayName("Игрок сдаётся")
    void inputGiveUp() {
        GameModel model = new GameModel(new String[] {"apple"});
        String[] actualResult = model.processInput("GIve uP");
        assertThat(actualResult).contains(
            InputReaction.GAVE_UP.getText());
    }

    @Test
    @DisplayName("Выход из игры")
    void inputQuit() {
        GameModel model = new GameModel(new String[] {"apple"});
        String[] actualResult = model.processInput("qUit");
        assertThat(actualResult).contains(
            InputReaction.CLOSE_THE_GAME.getText());
    }

    @Test
    @DisplayName("Победа")
    void win() {
        GameModel model = new GameModel(new String[] {"apple"});
        model.processInput("a");
        model.processInput("p");
        model.processInput("l");
        String[] actualResult = model.processInput("e");
        assertThat(actualResult).contains(
            InputReaction.WIN.getText());
    }

    @Test
    @DisplayName("Проигрыш")
    void lose() {
        GameModel model = new GameModel(new String[] {"apple"});
        model.processInput("f");
        model.processInput("d");
        model.processInput("k");
        model.processInput("m");
        model.processInput("o");
        String[] actualResult = model.processInput("z");
        assertThat(actualResult).contains(
            InputReaction.DEFEAT.getText());
    }
}
