package edu.project3.report.reporters;

import edu.project3.enums.Format;
import edu.project3.stats.MostRequestedSourcesStatistics;
import java.util.Map;

@SuppressWarnings("checkstyle:MultipleStringLiterals")
public class MostRequestedSourcesReport extends LogReport {
    private final MostRequestedSourcesStatistics mostRequestedSourcesStatistics;

    public MostRequestedSourcesReport(Format format, MostRequestedSourcesStatistics mostRequestedSourcesStatistics) {
        super(format);
        this.mostRequestedSourcesStatistics = mostRequestedSourcesStatistics;
    }

    @Override
    protected String createAdocReportTable() {
        Map<String, Long> mostFrequentResources = mostRequestedSourcesStatistics.getMostFrequentResources();
        StringBuilder result = new StringBuilder("==== Запрашиваемые ресурсы\n"
            + ADOC_TABLE_OPTIONS + ADOC_TABLE_BOUNDARY + END_LINE
            + COLUMN_SEPARATOR + "Ресурс" + COLUMN_SEPARATOR + "Количество" + END_LINE);
        for (var entry : mostFrequentResources.entrySet()) {
            result.append(COLUMN_SEPARATOR + entry.getKey() + COLUMN_SEPARATOR
                + entry.getValue() + END_LINE);
        }
        result.append(ADOC_TABLE_BOUNDARY);
        return result.toString();
    }

    @Override
    protected String createMdReportTable() {
        Map<String, Long> mostFrequentResources = mostRequestedSourcesStatistics.getMostFrequentResources();
        StringBuilder result = new StringBuilder("#### Запрашиваемые ресурсы\n"
            + COLUMN_SEPARATOR + "Ресурс" + COLUMN_SEPARATOR + "Количество" + COLUMN_SEPARATOR
            + END_LINE + "|:---:|---:|\n");
        for (var entry : mostFrequentResources.entrySet()) {
            result.append(COLUMN_SEPARATOR + QUOTE + entry.getKey() + QUOTE + COLUMN_SEPARATOR
                + entry.getValue() + COLUMN_SEPARATOR + END_LINE);
        }
        return result.toString();
    }
}
