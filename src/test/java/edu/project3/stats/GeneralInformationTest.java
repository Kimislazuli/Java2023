package edu.project3.stats;

import edu.project3.record.LogRecord;
import edu.project3.record.LogRecordConverter;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class GeneralInformationTest {
    Path path = Paths.get("src/test/resources/project3/test.logs");
    List<LogRecord> content = Files.lines(path).map(LogRecordConverter::parseLog).toList();
    GeneralInformation generalInformation = new GeneralInformation(Arrays.asList("test"), OffsetDateTime.MIN, OffsetDateTime.MAX, content);

    public GeneralInformationTest() throws IOException {
    }

    @Test
    @DisplayName("Корректное количество")
    void correctlyCountAmountOfRequests() {
        long actualResult = generalInformation.getAmountOfRequests();

        assertThat(actualResult).isEqualTo(5);
    }

    @Test
    @DisplayName("Корректное среднее значение")
    void correctlyCountAverageSize() {
        double actualResult = generalInformation.getAverageResponseSize();

        assertThat(actualResult).isCloseTo(272.4, Percentage.withPercentage(0.1));
    }
}
