package edu.project3.report.reporters;

import edu.project3.enums.Format;
import edu.project3.stats.PeekHourStatistics;
import java.util.Map;

@SuppressWarnings("checkstyle:MultipleStringLiterals")
public class PeakHourReporter extends LogReport {
    private final PeekHourStatistics peekHourStatistics;

    public PeakHourReporter(Format format, PeekHourStatistics peekHourStatistics) {
        super(format);
        this.peekHourStatistics = peekHourStatistics;
    }

    @Override protected String createAdocReportTable() {
        Map<Integer, Long> mostFrequentResources = peekHourStatistics.getMostActiveHours();
        StringBuilder result = new StringBuilder("==== Наиболее активные часы\n"
            + ADOC_TABLE_OPTIONS + ADOC_TABLE_BOUNDARY + END_LINE
            + COLUMN_SEPARATOR + "Час" + COLUMN_SEPARATOR + "Количество запросов" + END_LINE);
        for (var entry : mostFrequentResources.entrySet()) {
            result.append(COLUMN_SEPARATOR).append(entry.getKey().toString()).append(COLUMN_SEPARATOR)
                .append(entry.getValue()).append(END_LINE);
        }
        result.append(ADOC_TABLE_BOUNDARY);
        return result.toString();
    }

    @Override protected String createMdReportTable() {
        Map<Integer, Long> mostFrequentResources = peekHourStatistics.getMostActiveHours();
        StringBuilder result = new StringBuilder("#### Наиболее активные часы\n"
            + COLUMN_SEPARATOR + "Дата" + COLUMN_SEPARATOR + "Количество запросов" + COLUMN_SEPARATOR
            + END_LINE + "|:---:|---:|\n");
        for (var entry : mostFrequentResources.entrySet()) {
            result.append(COLUMN_SEPARATOR + QUOTE + entry.getKey().toString() + QUOTE + COLUMN_SEPARATOR
                + entry.getValue() + COLUMN_SEPARATOR + END_LINE);
        }
        return result.toString();
    }
}
