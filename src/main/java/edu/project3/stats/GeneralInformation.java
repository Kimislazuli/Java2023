package edu.project3.stats;

import edu.project3.record.LogRecord;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class GeneralInformation {
    private int sourcesAmount;
    private List<String> sources;
    private String startDate = "-";
    private String endDate = "-";
    private long amountOfRequests;
    private double averageResponseSize;

    public GeneralInformation(
        List<String> sources,
        List<LogRecord> logEntries
    ) {
        this.sources = sources;
        sourcesAmount = sources.size();
        amountOfRequests = countEntries(logEntries.stream());
        startDate = findStartDate(logEntries.stream());
        endDate = findEndDate(logEntries.stream());
        averageResponseSize = countAverageResponseSize(logEntries.stream());
    }

    private long countEntries(Stream<LogRecord> logEntries) {
        return logEntries.count();
    }

    private double countAverageResponseSize(Stream<LogRecord> logEntries) {
        return logEntries.mapToLong(LogRecord::bytesSent).average().orElse(0D);
    }

    private String findStartDate(Stream<LogRecord> logEntries) {
        return logEntries
            .map(LogRecord::dateTime)
            .min(Comparator.naturalOrder())
            .map(d -> d.toLocalDate().toString())
            .orElse("-");
    }

    private String findEndDate(Stream<LogRecord> logEntries) {
        return logEntries
            .map(LogRecord::dateTime)
            .max(Comparator.naturalOrder())
            .map(d -> d.toLocalDate().toString())
            .orElse("-");
    }

    public List<String> getSources() {
        return sources;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getAverageResponseSize() {
        return averageResponseSize;
    }

    public long getAmountOfRequests() {
        return amountOfRequests;
    }
}
