package edu.project3.stats;

import edu.project3.record.LogRecord;
import java.time.OffsetDateTime;
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
        OffsetDateTime startDate,
        OffsetDateTime endDate,
        List<LogRecord> logEntries
    ) {
        this.sources = sources;
        sourcesAmount = sources.size();
        if (startDate != null) {
            this.startDate = startDate.toLocalDate().toString();
        }
        if (endDate != null) {
            this.endDate = endDate.toLocalDate().toString();
        }
        amountOfRequests = countEntries(logEntries.stream());
        averageResponseSize = countAverageResponseSize(logEntries.stream());
    }

    private long countEntries(Stream<LogRecord> logEntries) {
        return logEntries.count();
    }

    private double countAverageResponseSize(Stream<LogRecord> logEntries) {
        return logEntries.mapToLong(LogRecord::bytesSent).average().orElse(0D);
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
