package edu.project3.stats;

import edu.project3.record.LogRecord;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MostFrequentStatusCodesStatistics {
    private static final int AMOUNT_OF_ENTRIES = 3;
    private Map<Integer, Long> mostFrequentStatusCodes;

    public MostFrequentStatusCodesStatistics(List<LogRecord> logRecords) {
        mostFrequentStatusCodes = parseStatusCodes(logRecords.stream());
    }

    private Map<Integer, Long> parseStatusCodes(Stream<LogRecord> logRecords) {
        return logRecords.map(LogRecord::status)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
            .limit(AMOUNT_OF_ENTRIES)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Long> getMostFrequentStatusCodes() {
        return mostFrequentStatusCodes;
    }
}
