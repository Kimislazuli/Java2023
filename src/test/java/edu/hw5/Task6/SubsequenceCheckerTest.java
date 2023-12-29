package edu.hw5.Task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task6.SubsequenceChecker.isSubsequence;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubsequenceCheckerTest {
    @Test
    @DisplayName("Null подпоследовательность")
    void nullSubsequence() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                isSubsequence(null, "abc");
            }
        );
    }

    @Test
    @DisplayName("Null целевая строка")
    void nullTarget() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                isSubsequence("abc", null);
            }
        );
    }

    @Test
    @DisplayName("Пустая подпоследовательность")
    void emptySubsequence() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                isSubsequence("", "abc");
            }
        );
    }

    @Test
    @DisplayName("Пустая целевая строка")
    void emptyTarget() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                isSubsequence("", "abc");
            }
        );
    }

    @Test
    @DisplayName("Подпоследовательность")
    void subsequence() {
        boolean actualResult = isSubsequence("abc", "saabsfccasefw");

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Не подпоследовательность")
    void notSubsequence() {
        boolean actualResult = isSubsequence("abc", "saabsfasefw");

        assertThat(actualResult).isFalse();
    }
}
