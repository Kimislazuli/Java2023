package edu.hw6.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.hw6.Task4.OutputStreamComposition.writeToFile;
import static org.assertj.core.api.Assertions.assertThat;

public class OutputStreamCompositionTest {
    Path path = Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "Task4" + File.separator + "file");
    String text = "Programming is learned by writing programs. ― Brian Kernighan";

    @Test
    @DisplayName("Файл записывается корректно")
    void writeToFileCorrectly() throws IOException {
        writeToFile(path, text);

        String actualResult = new String(Files.readAllBytes(path));
        assertThat(actualResult).contains(text);
    }
}
