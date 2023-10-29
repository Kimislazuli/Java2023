package edu.hw3.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task1.AtbashCipher.atbash;
import static org.assertj.core.api.Assertions.assertThat;

public class AtbashCipherTest {
    @Test
    @DisplayName("Строка с нижним регистром")
    void lowerCaseString() {
        String actualResult = atbash("hello");

        assertThat(actualResult).isEqualTo("svool");
    }

    @Test
    @DisplayName("Строка с верхним регистром")
    void upperCaseString() {
        String actualResult = atbash("WORLD");

        assertThat(actualResult).isEqualTo("DLIOW");
    }

    @Test
    @DisplayName("Строка со смешанным регистром")
    void mixedCaseString() {
        String actualResult = atbash("Any fool can write code that a computer can understand");

        assertThat(actualResult).isEqualTo("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw");
    }

    @Test
    @DisplayName("Строка с другими символами")
    void otherCharactersString() {
        String actualResult = atbash("Good programmers write code that humans can understand. ― Martin Fowler");

        assertThat(actualResult).isEqualTo("Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
    }

    @Test
    @DisplayName("Число")
    void numericString() {
        String actualResult = atbash("65");

        assertThat(actualResult).isEqualTo("65");
    }
}
