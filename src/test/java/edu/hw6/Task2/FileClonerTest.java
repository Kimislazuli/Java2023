package edu.hw6.Task2;

import edu.hw6.Task1.DiskMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;
import static edu.hw6.Task2.FileCloner.cloneFile;
import static org.assertj.core.api.Assertions.assertThat;

public class FileClonerTest {
    Path directory = Path.of(
        "src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator +
            "Task2");
    Path filepath = Path.of(directory + File.separator + "Tinkoff Bank Biggest Secret.txt");
    Logger LOGGER = LogManager.getLogger(DiskMap.class);

    void clearDirectory() {
        System.out.println(filepath);
        try (Stream<Path> fileTree = Files.walk(directory)) {
            fileTree.sorted(Comparator.reverseOrder())
                .forEach(file -> {
                        try {
                            Files.delete(file);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                );
            Files.createDirectory(directory);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("Создание файла в первый раз")
    void createFileAtTheFirstTime() throws IOException {
        clearDirectory();
        cloneFile(Path.of(directory + File.separator + "Tinkoff Bank Biggest Secret.txt"));

        boolean actualResult = Files.exists(filepath);
        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Создание файла во второй раз")
    void createFileAtTheSecondTime() throws IOException {
        clearDirectory();
        cloneFile(filepath);
        cloneFile(filepath);

        boolean actualResult =
            Files.exists(Path.of(directory + File.separator + "Tinkoff Bank Biggest Secret — копия.txt"));
        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Создание файла в третий раз")
    void createFileAtTheThirdTime() throws IOException {
        clearDirectory();
        cloneFile(filepath);
        cloneFile(filepath);
        cloneFile(filepath);

        boolean actualResult =
            Files.exists(Path.of(directory + File.separator + "Tinkoff Bank Biggest Secret — копия (2).txt"));
        assertThat(actualResult).isTrue();
    }
}
