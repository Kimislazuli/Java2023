package edu.project3.report.reporters;

import edu.project3.enums.Format;
import edu.project3.stats.MostFrequentStatusCodesStatistics;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"checkstyle:MagicNumber", "checkstyle:MultipleStringLiterals"})
public class MostFrequentStatusCodesReport extends LogReport {
    private final MostFrequentStatusCodesStatistics mostFrequentStatusCodesStatistics;
    private static final Map<Integer, String> STATUS_CODES = new HashMap<>();

    static {
        STATUS_CODES.put(100, "Continue");
        STATUS_CODES.put(101, "Switching Protocols");
        STATUS_CODES.put(102, "Processing ");
        STATUS_CODES.put(103, "Early Hints");
        STATUS_CODES.put(200, "OK");
        STATUS_CODES.put(201, "Created");
        STATUS_CODES.put(202, "Accepted");
        STATUS_CODES.put(203, "Non-Authoritative Information");
        STATUS_CODES.put(204, "No Content");
        STATUS_CODES.put(205, "Reset Content");
        STATUS_CODES.put(206, "Partial Content");
        STATUS_CODES.put(207, "Multi-Status");
        STATUS_CODES.put(208, "Already Reported");
        STATUS_CODES.put(226, "IM Used");
        STATUS_CODES.put(300, "Multiple Choices");
        STATUS_CODES.put(301, "Moved Permanently");
        STATUS_CODES.put(302, "Found");
        STATUS_CODES.put(303, "See Other");
        STATUS_CODES.put(304, "Not Modified");
        STATUS_CODES.put(305, "Use Proxy");
        STATUS_CODES.put(306, "Switch Proxy");
        STATUS_CODES.put(307, "Temporary Redirect");
        STATUS_CODES.put(308, "Permanent Redirect");
        STATUS_CODES.put(400, "Bad Request");
        STATUS_CODES.put(401, "Unauthorized");
        STATUS_CODES.put(402, "Payment Required");
        STATUS_CODES.put(403, "Forbidden");
        STATUS_CODES.put(404, "Not Found");
        STATUS_CODES.put(405, "Method Not Allowed");
        STATUS_CODES.put(406, "Not Acceptable");
        STATUS_CODES.put(407, "Proxy Authentication Required");
        STATUS_CODES.put(408, "Request Timeout");
        STATUS_CODES.put(409, "Conflict");
        STATUS_CODES.put(410, "Gone");
        STATUS_CODES.put(411, "Length Required ");
        STATUS_CODES.put(412, "Precondition Failed");
        STATUS_CODES.put(413, "Payload Too Large");
        STATUS_CODES.put(414, "URI Too Long");
        STATUS_CODES.put(415, "Unsupported Media Type");
        STATUS_CODES.put(416, "Range Not Satisfiable");
        STATUS_CODES.put(500, "Internal Server Error");
        STATUS_CODES.put(501, "Not Implemented");
        STATUS_CODES.put(502, "Bad Gateway");
        STATUS_CODES.put(503, "Service Unavailable");
        STATUS_CODES.put(504, "Gateway Timeout");
        STATUS_CODES.put(505, "HTTP Version Not Supported");
    }

    public MostFrequentStatusCodesReport(
        Format format,
        MostFrequentStatusCodesStatistics mostFrequentStatusCodesStatistics
    ) {
        super(format);
        this.mostFrequentStatusCodesStatistics = mostFrequentStatusCodesStatistics;
    }

    @Override
    protected String createAdocReportTable() {
        Map<Integer, Long> mostFrequentStatusCodes = mostFrequentStatusCodesStatistics.getMostFrequentStatusCodes();
        StringBuilder result = new StringBuilder("==== Коды ответа\n"
            + ADOC_TABLE_OPTIONS + ADOC_TABLE_BOUNDARY + END_LINE
            + COLUMN_SEPARATOR + "Код" + COLUMN_SEPARATOR + "Имя" + COLUMN_SEPARATOR + "Количество" + END_LINE);
        for (var entry : mostFrequentStatusCodes.entrySet()) {
            String statusName = STATUS_CODES.get(entry.getKey());
            if (statusName == null) {
                statusName = "-";
            }
            result.append(COLUMN_SEPARATOR + entry.getKey() + COLUMN_SEPARATOR + statusName + COLUMN_SEPARATOR
                + entry.getValue() + END_LINE);
        }
        result.append(ADOC_TABLE_BOUNDARY);
        return result.toString();
    }

    @Override
    protected String createMdReportTable() {
        Map<Integer, Long> mostFrequentStatusCodes = mostFrequentStatusCodesStatistics.getMostFrequentStatusCodes();
        StringBuilder result = new StringBuilder("#### Коды ответа\n"
            + COLUMN_SEPARATOR + "Код" + COLUMN_SEPARATOR + "Имя" + COLUMN_SEPARATOR + "Количество"
            + COLUMN_SEPARATOR + END_LINE + "|:---:|:---:|---:|\n");
        for (var entry : mostFrequentStatusCodes.entrySet()) {
            String statusName = STATUS_CODES.get(entry.getKey());
            if (statusName == null) {
                statusName = "-";
            }
            result.append(COLUMN_SEPARATOR + entry.getKey() + COLUMN_SEPARATOR + statusName + COLUMN_SEPARATOR
                + entry.getValue() + COLUMN_SEPARATOR + END_LINE);
        }

        return result.toString();
    }
}
