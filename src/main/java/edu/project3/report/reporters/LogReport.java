package edu.project3.report.reporters;

import edu.project3.enums.Format;

public abstract class LogReport {
    protected Format format;
    protected final static String COLUMN_SEPARATOR = "|";
    protected final static String ADOC_TABLE_BOUNDARY = "|====";
    protected final static String ADOC_TABLE_OPTIONS = "[%autowidth]\n[options=\"header\"]\n";
    protected final static String QUOTE = "`";
    protected final static String END_LINE = "\n";


    public LogReport(Format format) {
        this.format = format;
    }

    @SuppressWarnings("checkstyle:MissingSwitchDefault")
    public String getStatisticsTable() {
        switch (format) {
            case MD -> {
                return createMdReportTable();
            }
            case ADOC -> {
                return createAdocReportTable();
            }
        }
        return "";
    }

    abstract protected String createAdocReportTable();

    abstract protected String createMdReportTable();
}
