package edu.project3.stats;

import edu.project3.record.LogRecord;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PeekHourStatistics { private static final int AMOUNT_OF_ENTRIES = 3;
    private Map<Integer, Long> mostActiveHours;

    public PeekHourStatistics(List<LogRecord> logEntries) {
        mostActiveHours = findMostActiveHours(logEntries.stream());
    }

    private Map<Integer, Long> findMostActiveHours(Stream<LogRecord> logEntries) {
        return logEntries
            .map(e -> e.dateTime().getHour())
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
            .limit(AMOUNT_OF_ENTRIES)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Long> getMostActiveHours() {
        return mostActiveHours;
    }

}
