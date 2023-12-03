package edu.hw8.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordGuesserUtilsTest {
    @Test
    @DisplayName("Правильное составление md5 хэша")
    void correctlyFindM5() {
        String actualResult = PasswordGuesserUtils.md5Hasher("12");

        assertThat(actualResult).isEqualTo("c20ad4d76fe97759aa27a0c99bff6710");
    }

    @Test
    @DisplayName("Правильное составление md5 хэша")
    void correctlyGeneratePartialPermutations() {
        PasswordGuesserUtils.partialPermutationUtil(new String[]{"1", "2", "3", "4", "5"}, new String[4], 0, 4, 0, 4);
        int actualResult = PasswordGuesserUtils.getQueueSize();

        assertThat(actualResult).isEqualTo(625);
    }
}
