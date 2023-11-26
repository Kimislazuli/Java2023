package edu.project3.stats;

import edu.project3.record.LogRecord;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MostActiveDateStatistics {
    private static final int AMOUNT_OF_ENTRIES = 3;
    private Map<OffsetDateTime, Long> mostActiveDays;

    public MostActiveDateStatistics(List<LogRecord> logRecords) {
        mostActiveDays = parseDays(logRecords.stream());
    }

    private Map<OffsetDateTime, Long> parseDays(Stream<LogRecord> logRecords) {
        return logRecords.map(LogRecord::dateTime)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Map.Entry.<OffsetDateTime, Long>comparingByValue().reversed())
            .limit(AMOUNT_OF_ENTRIES)
            .collect(
                Collectors
                    .toMap(Map.Entry<OffsetDateTime, Long>::getKey, Map.Entry<OffsetDateTime, Long>::getValue)
            );
    }

    public Map<OffsetDateTime, Long> getMostActiveDays() {
        return mostActiveDays;
    }
}
