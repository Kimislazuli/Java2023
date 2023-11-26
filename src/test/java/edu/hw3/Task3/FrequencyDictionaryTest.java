package edu.hw3.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static edu.hw3.Task3.FrequencyDictionary.freqDictWithStreams;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FrequencyDictionaryTest {
    @Test
    @DisplayName("Частотный словарь на стримах с одним регистром")
    void freqDictWithStreamsForOneCaseList() {
        Map<String, Integer> actualResult = freqDictWithStreams(Arrays.asList("this", "and", "that", "and"));

        assertThat(actualResult).isEqualTo(Stream.of(new String[][] {
            {"that", "1"},
            {"and", "2"},
            {"this", "1"}
        }).collect(Collectors.toMap(data -> data[0], data -> Integer.valueOf(data[1]))));
    }

    @Test
    @DisplayName("Частотный словарь на стримах с разными регистрами")
    void freqDictWithStreamsForDifferentCasesList() {
        Map<String, Integer> actualResult = freqDictWithStreams(Arrays.asList("aa", "aA", "AA", "bb"));

        assertThat(actualResult).isEqualTo(Stream.of(new String[][] {
            {"aa", "3"},
            {"bb", "1"}
        }).collect(Collectors.toMap(data -> data[0], data -> Integer.valueOf(data[1]))));
    }

    @Test
    @DisplayName("Частотный словарь на стримах с разными регистрами")
    void freqDictWithStreamsForDifferentLanguages() {
        Map<String, Integer> actualResult = freqDictWithStreams(Arrays.asList("код", "код", "код", "bug"));

        assertThat(actualResult).isEqualTo(Stream.of(new String[][] {
            {"код", "3"},
            {"bug", "1"}
        }).collect(Collectors.toMap(data -> data[0], data -> Integer.valueOf(data[1]))));
    }

    @Test
    @DisplayName("Частотный словарь пустого списка")
    void freqDictWithStreamsForEmptyList() {
        Map<String, Integer> actualResult = freqDictWithStreams(Arrays.asList());

        assertThat(actualResult).isEqualTo(Collections.emptyMap());
    }

    @Test
    @DisplayName("Частотный словарь null")
    void freqDictWithStreamsForNull() {
        assertThrows(
            IllegalArgumentException.class,
            () -> freqDictWithStreams(null)
        );
    }
}
