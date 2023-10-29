package edu.hw3.Task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.hw3.Task2.ParenthesisClusterizer.clusterize;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParenthesisClusterizerTest {
    @Test
    @DisplayName("Неправильный ввод")
    void wrongInput() {
        assertThrows(
            IllegalArgumentException.class,
            () -> clusterize("abc123")
        );
    }

    @Test
    @DisplayName("Несбалансированные скобки")
    void notBalancedParentheses() {
        assertThrows(
            IllegalArgumentException.class,
            () -> clusterize(")(")
        );
    }

    @Test
    @DisplayName("Null")
    void nullInput() {
        assertThrows(
            IllegalArgumentException.class,
            () -> clusterize(null)
        );
    }

    @Test
    @DisplayName("Вложенные скобки")
    void nestedParentheses() {
        List<String> actualResult = clusterize("((()))(())()()(()())");

        assertThat(actualResult).containsExactly("((()))", "(())", "()", "()", "(()())");
    }

    @Test
    @DisplayName("Не вложенные скобки")
    void notNestedParentheses() {
        List<String> actualResult = clusterize("()()()");

        assertThat(actualResult).containsExactly("()", "()", "()");
    }
}
