package edu.hw3.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task4.RomanNumbers.convertToRoman;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RomanNumbersTest {
    @Test
    @DisplayName("Число, превышающее 3999")
    void moreThan4999() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                convertToRoman(5444);
            }
        );
    }

    @Test
    @DisplayName("0")
    void zero() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                convertToRoman(0);
            }
        );
    }

    @Test
    @DisplayName("Составное число")
    void compositeNumber() {
        String actualResult = convertToRoman(3992);

        assertThat(actualResult).isEqualTo("MMMCMXCII");
    }

    @Test
    @DisplayName("Число из сответствия")
    void simpleNumber() {
        String actualResult = convertToRoman(9);

        assertThat(actualResult).isEqualTo("IX");
    }
}
