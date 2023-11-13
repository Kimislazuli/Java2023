package edu.hw5.Task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task8.ExtendedBinaryValidator.everyEvenIsOne;
import static edu.hw5.Task8.ExtendedBinaryValidator.noNeighbourOnes;
import static edu.hw5.Task8.ExtendedBinaryValidator.oddLength;
import static edu.hw5.Task8.ExtendedBinaryValidator.oddLengthIfStartsWIthZeroEvenIfFromOne;
import static edu.hw5.Task8.ExtendedBinaryValidator.threeNZeros;
import static org.assertj.core.api.Assertions.assertThat;

public class ExtendedBinaryValidatorTest {
    @Test
    @DisplayName("Нечётная длина")
    void oddLengthTest() {
        boolean actualResult = oddLength("101");

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Чётная длина")
    void evenLengthTest() {
        boolean actualResult = oddLength("10");

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Нечётная длина с нуля")
    void oddLengthFromZero() {
        boolean actualResult = oddLengthIfStartsWIthZeroEvenIfFromOne("010");

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Чётная длина с единицы")
    void evenLengthFromOne() {
        boolean actualResult = oddLengthIfStartsWIthZeroEvenIfFromOne("10");

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Нечётная длина с нуля")
    void oddLengthFromOne() {
        boolean actualResult = oddLengthIfStartsWIthZeroEvenIfFromOne("110");

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Чётная длина с единицы")
    void evenLengthFromZero() {
        boolean actualResult = oddLengthIfStartsWIthZeroEvenIfFromOne("00");

        assertThat(actualResult).isFalse();
    }


    @Test
    @DisplayName("Количество нулей кратно трём")
    void threeNZerosTest() {
        boolean actualResult = threeNZeros("1001000110");

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Количество нулей не кратно трём")
    void notThreeNZeros() {
        boolean actualResult = threeNZeros("00000010");

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Каждое чётное -- единица")
    void everyEvenIsOneTest() {
        boolean actualResult = everyEvenIsOne("1111010111");

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Не все чётные единицы")
    void notEveryEvenIsOne() {
        boolean actualResult = everyEvenIsOne("01010010");

        assertThat(actualResult).isFalse();
    }
    @Test
    @DisplayName("Нет соседствующих единиц")
    void noNeighbourOnesTest() {
        boolean actualResult = noNeighbourOnes("01010010");

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Есть единицы по соседству")
    void neighbourOnes() {
        boolean actualResult = noNeighbourOnes("1101111101");

        assertThat(actualResult).isFalse();
    }
}
