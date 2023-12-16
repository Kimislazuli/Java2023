package edu.project3.record;

import java.time.OffsetDateTime;

public record LogRecord(
    String remoteAddr,
    String remoteUser,
    OffsetDateTime dateTime,
    String requestType,
    String resource,
    int status,
    long bytesSent,
    String httpReferer,
    String httpUserAgent) {
}
