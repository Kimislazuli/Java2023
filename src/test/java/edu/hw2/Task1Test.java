package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw2.Task1.Expr;
import static edu.hw2.Task1.Expr.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {

    @Test
    @DisplayName("Добавление константы")
    void addConstant() {
        Expr constant = new Constant(5);

        double actualResult = constant.evaluate();

        assertThat(actualResult).isEqualTo(5);
    }

    @Test
    @DisplayName("\"Отрицание\" положительного числа")
    void negatePositiveNumber() {
        Expr negate = new Negate(new Constant(8));

        double actualResult = negate.evaluate();

        assertThat(actualResult).isEqualTo(-8);
    }

    @Test
    @DisplayName("\"Отрицание\" отрицательного числа")
    void negateNegativeNumber() {
        Expr negate = new Negate(new Constant(-2));

        double actualResult = negate.evaluate();

        assertThat(actualResult).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource({
        "5, 82, 87",
        "-5, 82, 77",
        "5, -82, -77",
        "-5, -82, -87",
        "0, 2, 2",
        "9, 0, 9",
        "0, 0, 0",
        "0.4, 2, 2.4",
        "0.2, 0.2, 0.4"
    })
    @DisplayName("Cумма")
    void sum(double first, double second, double expectedResult) {
        Expr sum = new Addition(new Constant(first), new Constant(second));

        double actualResult = sum.evaluate();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
        "5, 4, 20",
        "0, 8, 0",
        "5, -2, -10",
        "-5, 2, -10",
        "0, 2, 0",
        "9, 0, 0",
        "0, 0, 0",
        "0.4, 2, 0.8",
        "0.4, 0.4, 0.16"
    })
    @DisplayName("Умножение")
    void multiplication(double first, double second, double expectedResult) {
        Expr multiply = new Multiplication(new Constant(first), new Constant(second));

        double actualResult = multiply.evaluate();

        assertThat(actualResult).isEqualTo(expectedResult, withPrecision(2d));
    }

    @ParameterizedTest
    @CsvSource({
        "256, 0.25, 4",
        "0, 1, 0",
        "1, 1000, 1",
        "-5, 2, 25",
        "5, 2, 25",
        "2, 0.5, 1.4",
        "0, 0.5, 0",
        "1, 0.7, 1"
    })
    @DisplayName("Возведение в степень")
    void exponentiation(double first, double second, double expectedResult) {
        Expr exponent = new Exponent(new Constant(first), second);

        double actualResult = exponent.evaluate();

        assertThat(actualResult).isEqualTo(expectedResult, withPrecision(2d));
    }

    @Test
    @DisplayName("Отрицательное число в дробной степени")
    void nonIntegerPowerForNegative() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Expr exponent = new Exponent(new Constant(-5), 0.5);

                exponent.evaluate();
            }
        );
    }

    @Test
    @DisplayName("0 в степени 0")
    void zeroInZeroPower() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Expr exponent = new Exponent(new Constant(0), 0);

                exponent.evaluate();
            }
        );
    }

}
