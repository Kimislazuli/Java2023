package edu.hw6.Task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.net.SocketException;
import java.util.List;
import static edu.hw6.Task6.PortScanner.scanPorts;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PortScannerTest {
    @Test
    @DisplayName("Корректно отрабатывает")
    void correctlyWorks() {
        assertDoesNotThrow(PortScanner::scanPorts);
    }

    @Test
    @DisplayName("Возвращает столбцы таблицы")
    void returnsTableRows() throws SocketException {
        List<String> actualResult = scanPorts();

        assertThat(actualResult.getLast()).matches(".{0,10}\\s*\\d{0,6}\\s*.{0,40}\n");
    }
}
