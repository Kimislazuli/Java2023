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

public class MostRequestedSourcesStatisticsTest {
    Path path = Paths.get("src/test/resources/project3/test.logs");
    List<LogRecord> content = Files.lines(path).map(LogRecordConverter::parseLog).toList();
    MostRequestedSourcesStatistics mostRequestedSourcesStatistics = new MostRequestedSourcesStatistics(content);

    public MostRequestedSourcesStatisticsTest() throws IOException {
    }

    @Test
    @DisplayName("Корректно определяются самые популярные ресурсы")
    void correctlyFindMostPopularCodes() {
        Map<String, Long> actualResult = mostRequestedSourcesStatistics.getMostFrequentResources();
        Map<String, Long> expectedResult = new HashMap<>();
        expectedResult.put("/downloads/product_1", 4L);
        expectedResult.put("/downloads/product_2", 1L);

        assertThat(actualResult).containsExactlyInAnyOrderEntriesOf(expectedResult);
    }
}
