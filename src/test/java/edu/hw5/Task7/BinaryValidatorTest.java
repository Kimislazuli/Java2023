package edu.hw5.Task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task7.BinaryValidator.atLeastThreeSymbolsAndThirdIsZero;
import static edu.hw5.Task7.BinaryValidator.lengthFromOneToThree;
import static edu.hw5.Task7.BinaryValidator.startsAndEndsWithTheSameLetter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BinaryValidatorTest {
    @Test
    @DisplayName("Некорректная строка")
    void invalidString() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                atLeastThreeSymbolsAndThirdIsZero("123sdf");
            }
        );
    }

    @Test
    @DisplayName("Три символа, 3 ноль")
    void threeSymbolsAndThirdIsZero() {
        boolean actualResult = atLeastThreeSymbolsAndThirdIsZero("010");

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Более трёх символов, 3 ноль")
    void moreThanThreeSymbolsAndThirdIsZeroTest() {
        boolean actualResult = atLeastThreeSymbolsAndThirdIsZero("11010");

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Менее трёх символов")
    void lessThanThreeSymbols() {
        boolean actualResult = atLeastThreeSymbolsAndThirdIsZero("10");

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Более трёх символов, 3 единица")
    void threeSymbolsAndThirdIsOne() {
        boolean actualResult = atLeastThreeSymbolsAndThirdIsZero("101");

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Начинается и заканчивается одним символом")
    void startsAndEndsWithTheSameLetterTest() {
        boolean actualResult = startsAndEndsWithTheSameLetter("01011010100");

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Начинается и заканчивается разными символами")
    void startsAndEndsWithDifferentLetters() {
        boolean actualResult = startsAndEndsWithTheSameLetter("11010");

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Длина 2")
    void lengthTwo() {
        boolean actualResult = lengthFromOneToThree("10");

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Длина 4")
    void lengthFour() {
        boolean actualResult = lengthFromOneToThree("1011");

        assertThat(actualResult).isFalse();
    }
}
