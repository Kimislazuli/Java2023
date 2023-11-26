package edu.project3.stats;

import edu.project3.record.LogRecord;
import edu.project3.record.LogRecordConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class MostActiveDateStatisticsTest {
    Path path = Paths.get("src/test/resources/project3/test.logs");
    List<LogRecord> content = Files.lines(path).map(LogRecordConverter::parseLog).toList();
    MostActiveDateStatistics mostActiveDateStatistics = new MostActiveDateStatistics(content);

    public MostActiveDateStatisticsTest() throws IOException {
    }

    @Test
    @DisplayName("Корректно определяются самые популярные ошибки")
    void correctlyFindMostPopularCodes() {
        Map<OffsetDateTime, Long> actualResult = mostActiveDateStatistics.getMostActiveDays();

        Map<OffsetDateTime, Long> expectedResult = new HashMap<>();
        expectedResult.put(OffsetDateTime.parse("2015-05-19T13:05:34Z"), 1L);
        expectedResult.put(OffsetDateTime.parse("2015-05-19T13:05:28Z"), 1L);
        expectedResult.put(OffsetDateTime.parse("2015-05-19T13:05:52Z"), 1L);

        assertThat(actualResult).containsExactlyInAnyOrderEntriesOf(expectedResult);
    }
}
