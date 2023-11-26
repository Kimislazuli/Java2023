package edu.hw3.Task3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class FrequencyDictionary {
    private FrequencyDictionary() {
    }

    public static Map<String, Integer> freqDictWithStreams(List<String> wordList) {
        if (wordList == null) {
            throw new IllegalArgumentException("List shouldn't be null");
        }
        Map<String, Integer> frequencyDictionary = wordList
            .stream()
            .map(String::toLowerCase)
            .collect(Collectors.toMap(
                key -> key, value -> 1, Integer::sum));
        return frequencyDictionary;
    }
}
