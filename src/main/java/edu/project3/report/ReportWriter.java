package edu.project3.report;

import edu.project3.enums.Format;
import edu.project3.report.reporters.LogReport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class ReportWriter {
    private ReportWriter() {
    }

    @SuppressWarnings("checkstyle:MissingSwitchDefault")
    public static void writeStatistics(Format format, LogReport... reports) throws IOException {
        Path path = Paths.get(System.getProperty("user.home"), ".logsReports", "report");
        switch (format) {
            case MD -> {
                path = Path.of(path + ".md");
            }
            case ADOC -> {
                path = Path.of(path + ".adoc");
            }
        }

        Files.deleteIfExists(path);
        Files.createDirectories(path.getParent());
        Files.createFile(path);

        StringBuilder wholeReport = new StringBuilder(reports[0].getStatisticsTable() + "\n");

        for (int i = 1; i < reports.length; i++) {
            wholeReport.append(reports[i].getStatisticsTable() + "\n");
        }

        Files.write(path, wholeReport.toString().getBytes());
    }
}
