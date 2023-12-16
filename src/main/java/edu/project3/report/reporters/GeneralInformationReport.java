package edu.project3.report.reporters;

import edu.project3.enums.Format;
import edu.project3.stats.GeneralInformation;
import java.text.DecimalFormat;
import java.util.List;

@SuppressWarnings("checkstyle:MultipleStringLiterals")
public class GeneralInformationReport extends LogReport {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("##.##");
    private final GeneralInformation generalInformation;

    public GeneralInformationReport(Format format, GeneralInformation generalInformation) {
        super(format);
        this.generalInformation = generalInformation;
    }

    protected String createAdocReportTable() {
        List<String> sources = generalInformation.getSources();
        StringBuilder result = new StringBuilder("==== Общая информация\n"
            + ADOC_TABLE_OPTIONS + ADOC_TABLE_BOUNDARY + END_LINE
            + COLUMN_SEPARATOR + "Метрика" + COLUMN_SEPARATOR + "Значение" + END_LINE);
        result.append(
            COLUMN_SEPARATOR + "Файл(-ы)" + COLUMN_SEPARATOR + sources.get(0) + END_LINE);
        for (int i = 1; i < sources.size(); i++) {
            result.append(
                COLUMN_SEPARATOR + " " + COLUMN_SEPARATOR + sources.get(i) + END_LINE);
        }
        result.append(COLUMN_SEPARATOR + "Начальная дата" + COLUMN_SEPARATOR + generalInformation.getStartDate()
            + END_LINE);
        result.append(COLUMN_SEPARATOR + "Конечная дата" + COLUMN_SEPARATOR + generalInformation.getEndDate()
            + END_LINE);
        result.append(COLUMN_SEPARATOR + "Количество запросов" + COLUMN_SEPARATOR
            + generalInformation.getAmountOfRequests() + END_LINE);
        result.append(
            COLUMN_SEPARATOR + "Средний размер ответа" + COLUMN_SEPARATOR
                + DECIMAL_FORMAT.format(generalInformation.getAverageResponseSize()) + END_LINE);
        result.append(ADOC_TABLE_BOUNDARY);
        return result.toString();
    }


    protected String createMdReportTable() {
        List<String> sources = generalInformation.getSources();
        StringBuilder result = new StringBuilder("#### Общая информация\n"
            + COLUMN_SEPARATOR + "Метрика" + COLUMN_SEPARATOR + "Значение" + COLUMN_SEPARATOR + END_LINE
            + "|:---:|---:|\n");
        result.append(
            COLUMN_SEPARATOR + "Файл(-ы)" + COLUMN_SEPARATOR + QUOTE + sources.get(0) + QUOTE + COLUMN_SEPARATOR
                + END_LINE);
        for (int i = 1; i < sources.size(); i++) {
            result.append(
                COLUMN_SEPARATOR + " " + COLUMN_SEPARATOR + QUOTE + sources.get(i) + QUOTE + COLUMN_SEPARATOR
                    + END_LINE);
        }
        result.append(COLUMN_SEPARATOR + "Начальная дата" + COLUMN_SEPARATOR + generalInformation.getStartDate()
            + COLUMN_SEPARATOR + END_LINE);
        result.append(COLUMN_SEPARATOR + "Конечная дата" + COLUMN_SEPARATOR + generalInformation.getEndDate()
            + COLUMN_SEPARATOR + END_LINE);
        result.append(COLUMN_SEPARATOR + "Количество запросов" + COLUMN_SEPARATOR
            + generalInformation.getAmountOfRequests() + COLUMN_SEPARATOR + END_LINE);
        result.append(
            COLUMN_SEPARATOR + "Средний размер ответа" + COLUMN_SEPARATOR
                + DECIMAL_FORMAT.format(generalInformation.getAverageResponseSize()) + COLUMN_SEPARATOR);
        return result.toString();
    }
}
