package edu.project3.stats;

import edu.project3.record.LogRecord;
import edu.project3.record.LogRecordConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class MostFrequentStatusCodesStatisticsTest {
    Path path = Paths.get("src/test/resources/project3/test.logs");
    List<LogRecord> content = Files.lines(path).map(LogRecordConverter::parseLog).toList();
    MostFrequentStatusCodesStatistics mostFrequentStatusCodesStatistics = new MostFrequentStatusCodesStatistics(content);

    public MostFrequentStatusCodesStatisticsTest() throws IOException {
    }

    @Test
    @DisplayName("Корректно определяются самые популярные ошибки")
    void correctlyFindMostPopularCodes() {
        Map<Integer, Long> actualResult = mostFrequentStatusCodesStatistics.getMostFrequentStatusCodes();
        Map<Integer, Long> expectedResult = new HashMap<>();
        expectedResult.put(404, 4L);
        expectedResult.put(304, 1L);

        assertThat(actualResult).containsExactlyInAnyOrderEntriesOf(expectedResult);
    }
}
