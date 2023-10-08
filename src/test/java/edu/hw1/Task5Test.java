package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw1.Task5.isPalindromeDescendant;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Чётное число-палиндром")
    void evenPalindrome() {
        int number = 2332;

        boolean actualResult = isPalindromeDescendant(number);

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Нечётное число-палиндром")
    void oddPalindrome() {
        int number = 123454321;

        boolean actualResult = isPalindromeDescendant(number);

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Чётный не палиндром с потомком-палиндромом")
    void evenNumberWithPalindromeChild() {
        int number = 22445588;

        boolean actualResult = isPalindromeDescendant(number);

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Нечётный не палиндром с потомком-палиндромом")
    void oddNumberWithPalindromeChild() {
        int number = 1234703;

        boolean actualResult = isPalindromeDescendant(number);

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Чётный не палиндром без потомков-палиндромов")
    void evenNumberWithoutPalindromeChild() {
        int number = 22445577;

        boolean actualResult = isPalindromeDescendant(number);

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Нечётный не палиндром без потомков-палиндромов")
    void oddNumberWithoutPalindromeChild() {
        int number = 1234567;

        boolean actualResult = isPalindromeDescendant(number);

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Число из одного разряда -- палиндром")
    void zero() {
        int number = 7;

        boolean actualResult = isPalindromeDescendant(number);

        assertThat(actualResult).isTrue();
    }
}
