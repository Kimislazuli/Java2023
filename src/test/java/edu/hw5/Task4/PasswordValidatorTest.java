package edu.hw5.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw5.Task4.PasswordValidator.isPasswordValid;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PasswordValidatorTest {
    @ParameterizedTest()
    @CsvSource({
        "1~1231sfw",
        "1!1231sfw",
        "1@1231sfw",
        "1#1231sfw",
        "1$1231sfw",
        "1%1231sfw",
        "1^1231sfw",
        "1&1231sfw",
        "1*1231sfw",
        "1|1231sfw",
        "~!@#$%^&*|"
    }
    )
    @DisplayName("Содержит символы")
    void contains(String string) {
        boolean actualResult = isPasswordValid(string);

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Не содержит символы")
    void notContains() {
        boolean actualResult = isPasswordValid("dfjhgjws");

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("null")
    void nullString() {
        assertThrows(
            IllegalArgumentException.class,
            () -> isPasswordValid(null)
        );
    }
}
