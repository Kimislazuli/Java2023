package edu.project3.record;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LogRecordConverter {
    private LogRecordConverter() {}

    private final static Logger LOGGER = LogManager.getLogger(LogRecordConverter.class);
    private static final Pattern LOG_PATTERN = Pattern
        .compile("^(.*) - (.*) \\[(.*)] \"(\\w+) (.*) HTTP/1.1\" (\\d{3}) (\\d+) \"(.+)\" \"(.*)\"");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z");

    @SuppressWarnings("checkstyle:MagicNumber")
    public static LogRecord parseLog(String log) {
        Matcher matcher = LOG_PATTERN.matcher(log);
        if (matcher.matches()) {
            String remoteAddr = matcher.group(1);
            String remoteUser = matcher.group(2);
            OffsetDateTime timeLocal = OffsetDateTime.parse(matcher.group(3), TIME_FORMAT);
            String requestType = matcher.group(4);
            String resource = matcher.group(5);
            int status = Integer.parseInt(matcher.group(6));
            long bytesSent = Long.parseLong(matcher.group(7));
            String httpReferer = matcher.group(8);
            String httpUserAgent = matcher.group(9);
            return new LogRecord(
                remoteAddr,
                remoteUser,
                timeLocal,
                requestType,
                resource,
                status,
                bytesSent,
                httpReferer,
                httpUserAgent
            );
        } else {
            LOGGER.error("Invalid log entry format " + log);
        }
        return null;
    }
}
