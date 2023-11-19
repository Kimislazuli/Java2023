package edu.project3;

import edu.project3.enums.Format;
import edu.project3.enums.Key;
import edu.project3.record.LogRecord;
import edu.project3.record.LogRecordConverter;
import edu.project3.report.ReportWriter;
import edu.project3.report.reporters.GeneralInformationReport;
import edu.project3.report.reporters.MostFrequentStatusCodesReport;
import edu.project3.report.reporters.MostRequestedSourcesReport;
import edu.project3.stats.GeneralInformation;
import edu.project3.stats.MostFrequentStatusCodesStatistics;
import edu.project3.stats.MostRequestedSourcesStatistics;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LogParser {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String URL_START = "https:";
    private final static Logger LOGGER = LogManager.getLogger(LogRecordConverter.class);
    private final static HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private final static List<String> SOURCES = new ArrayList<>();
    private static final String COMMAND_LINE_ERROR_MESSAGE = "Wrong command line arguments";
    private static Format format = Format.MD;
    private static OffsetDateTime from = null;
    private static OffsetDateTime to = null;

    private LogParser() {
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private static void parseArgs(String[] args) {
        Key currentKey = null;
        for (String argument : args) {
            if (argument.equals(Key.PATH.getKeyValue())) {
                currentKey = Key.PATH;
                continue;
            }

            if (argument.equals(Key.FORMAT.getKeyValue())) {
                currentKey = Key.FORMAT;
                continue;
            }

            if (argument.equals(Key.FROM.getKeyValue())) {
                currentKey = Key.FROM;
                continue;
            }

            if (argument.equals(Key.TO.getKeyValue())) {
                currentKey = Key.TO;
                continue;
            }

            if (currentKey == null) {
                throw new IllegalArgumentException(COMMAND_LINE_ERROR_MESSAGE);
            } else if (currentKey == Key.PATH) {
                SOURCES.add(argument);
            } else if (currentKey == Key.FORMAT) {
                if (argument.equals(Format.MD.getText())) {
                    format = Format.MD;
                } else {
                    format = Format.ADOC;
                }
            } else if (currentKey == Key.FROM) {
                from = LocalDate.parse(argument, DATE_TIME_FORMATTER).atStartOfDay().atOffset(ZoneOffset.UTC);
                currentKey = null;
            } else if (currentKey == Key.TO) {
                to = LocalDate.parse(argument, DATE_TIME_FORMATTER).atTime(23, 59).atOffset(ZoneOffset.UTC);
                currentKey = null;
            } else {
                throw new IllegalArgumentException(COMMAND_LINE_ERROR_MESSAGE);
            }
        }

        if (SOURCES.isEmpty()) {
            throw new IllegalArgumentException(COMMAND_LINE_ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        parseArgs(args);
        Stream<String> entries = parseAllSources();
        Stream<LogRecord> entriesAsRecords = entries.map(LogRecordConverter::parseLog);
        if (from != null) {
            entriesAsRecords = entriesAsRecords.filter(e -> e.localTime().isAfter(from));
        }
        if (to != null) {
            entriesAsRecords = entriesAsRecords.filter(e -> e.localTime().isBefore(to));
        }
        List<LogRecord> logRecords = entriesAsRecords.toList();
        GeneralInformation generalInformation = new GeneralInformation(SOURCES, from, to, logRecords);
        MostRequestedSourcesStatistics
            mostRequestedSourcesStatistics = new MostRequestedSourcesStatistics(logRecords);
        MostFrequentStatusCodesStatistics
            mostFrequentStatusCodes = new MostFrequentStatusCodesStatistics(logRecords);
        ReportWriter.writeStatistics(
            format,
            new GeneralInformationReport(format, generalInformation),
            new MostRequestedSourcesReport(format, mostRequestedSourcesStatistics),
            new MostFrequentStatusCodesReport(format, mostFrequentStatusCodes)
        );
    }

    private static Stream<String> parseAllSources() throws IOException, URISyntaxException, InterruptedException {
        Stream<String> logEntryStream;
        if (SOURCES.get(0).startsWith(URL_START)) {
            logEntryStream = getLinesFromRequest(SOURCES.get(0));
        } else {
            logEntryStream = getLinesOfFileStream(SOURCES.get(0));
        }

        for (int i = 1; i < SOURCES.size(); i++) {
            if (SOURCES.get(0).startsWith(URL_START)) {
                logEntryStream = Stream.concat(logEntryStream, getLinesFromRequest(SOURCES.get(0)));
            } else {
                logEntryStream = Stream.concat(logEntryStream, getLinesOfFileStream(SOURCES.get(0)));
            }
        }
        return logEntryStream;
    }

    private static Stream<String> getLinesFromRequest(String url)
        throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(url))
            .GET()
            .build();

        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body().lines();
    }

    private static Stream<String> getLinesOfFileStream(String filename) throws IOException {
        Path path = Paths.get(filename);
        if (Files.exists(path)) {
            return Files.lines(path);
        }
        LOGGER.error("No such file: " + filename);
        return Stream.empty();
    }
}
