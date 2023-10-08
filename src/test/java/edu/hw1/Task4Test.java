package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw1.Task4.fixString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task4Test {
    @Test
    @DisplayName("Строка чётной длины")
    void stringWithEvenLength() {
        String wrongString = "hTsii  s aimex dpus rtni.g";

        String actualResult = fixString(wrongString);

        assertThat(actualResult).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Строка нечётной длины")
    void stringWithOddLength() {
        String wrongString = "badce";

        String actualResult = fixString(wrongString);

        assertThat(actualResult).isEqualTo("abcde");
    }

    @Test
    @DisplayName("Пустая строка")
    void emptyString() {
        String wrongString = "";

        String actualResult = fixString(wrongString);

        assertThat(actualResult).isEqualTo("");
    }

    @Test
    @DisplayName("Null")
    void nullString() {
        assertThrows(
            NullPointerException.class,
            () -> {
                String wrongString = null;

                fixString(wrongString);
            }
        );
    }
}
