package edu.hw5.Task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task5.CarNumberValidator.isNumberValid;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarNumberValidatorTest {
    @Test
    @DisplayName("null")
    void nullString() {
        assertThrows(
            IllegalArgumentException.class,
            () -> isNumberValid(null)
        );
    }

    @Test
    @DisplayName("Корректный номер")
    void notContains() {
        boolean actualResult = isNumberValid("А123ВЕ777");

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Некорректные символы")
    void wrongSymbols() {
        boolean actualResult = isNumberValid("А123ВГ77");

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Длина превышает необходжимую")
    void longerThanTemplate() {
        boolean actualResult = isNumberValid("А123ВЕ7777");

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Короче шаблона")
    void shorterThanTemplate() {
        boolean actualResult = isNumberValid("123АВЕ777");

        assertThat(actualResult).isFalse();
    }
}
