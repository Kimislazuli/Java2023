package edu.project3.report.reporters;

import edu.project3.enums.Format;
import edu.project3.stats.MostActiveDateStatistics;
import java.time.OffsetDateTime;
import java.util.Map;

@SuppressWarnings("checkstyle:MultipleStringLiterals")
public class MostActiveDateReport extends LogReport {
    private final MostActiveDateStatistics mostActiveDateStatistics;

    public MostActiveDateReport(Format format, MostActiveDateStatistics mostActiveDateStatistics) {
        super(format);
        this.mostActiveDateStatistics = mostActiveDateStatistics;
    }

    @Override
    protected String createAdocReportTable() {
        Map<OffsetDateTime, Long> mostFrequentResources = mostActiveDateStatistics.getMostActiveDays();
        StringBuilder result = new StringBuilder("==== Наиболее активные дни\n"
            + ADOC_TABLE_OPTIONS + ADOC_TABLE_BOUNDARY + END_LINE
            + COLUMN_SEPARATOR + "Дата" + COLUMN_SEPARATOR + "Количество запросов" + END_LINE);
        for (var entry : mostFrequentResources.entrySet()) {
            result.append(COLUMN_SEPARATOR + entry.getKey().toLocalDate().toString() + COLUMN_SEPARATOR
                + entry.getValue() + END_LINE);
        }
        result.append(ADOC_TABLE_BOUNDARY);
        return result.toString();
    }

    @Override
    protected String createMdReportTable() {
        Map<OffsetDateTime, Long> mostFrequentResources = mostActiveDateStatistics.getMostActiveDays();
        StringBuilder result = new StringBuilder("#### Наиболее активные дни\n"
            + COLUMN_SEPARATOR + "Дата" + COLUMN_SEPARATOR + "Количество запросов" + COLUMN_SEPARATOR
            + END_LINE + "|:---:|---:|\n");
        for (var entry : mostFrequentResources.entrySet()) {
            result.append(COLUMN_SEPARATOR + QUOTE + entry.getKey().toLocalDate().toString() + QUOTE + COLUMN_SEPARATOR
                + entry.getValue() + COLUMN_SEPARATOR + END_LINE);
        }
        return result.toString();
    }
}
