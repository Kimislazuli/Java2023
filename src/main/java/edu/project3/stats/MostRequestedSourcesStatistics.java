package edu.project3.stats;

import edu.project3.record.LogRecord;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MostRequestedSourcesStatistics {
    private static final int AMOUNT_OF_ENTRIES = 3;
    private Map<String, Long> mostFrequentResources;

    public MostRequestedSourcesStatistics(List<LogRecord> logEntries) {
        mostFrequentResources = findMoreFrequentResources(logEntries.stream());
    }

    private Map<String, Long> findMoreFrequentResources(Stream<LogRecord> logEntries) {
        return logEntries
            .map(LogRecord::resource)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(AMOUNT_OF_ENTRIES)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Long> getMostFrequentResources() {
        return mostFrequentResources;
    }
}
