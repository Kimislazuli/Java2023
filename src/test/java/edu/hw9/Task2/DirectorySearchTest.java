package edu.hw9.Task2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;
import edu.hw9.Task2.directory.DirectoryInfo;
import edu.hw9.Task2.directory.DirectorySearch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DirectorySearchTest {

    static Path testDirectory;

    @BeforeAll
    static void setUp() throws IOException {
        testDirectory = Files.createTempDirectory("test");

        Files.createFile(testDirectory.resolve("1"));
        Files.createFile(testDirectory.resolve("2"));

        Path subdirectory1 = Files.createDirectory(testDirectory.resolve("dir1"));
        Files.createFile(subdirectory1.resolve("3"));
        Files.createFile(subdirectory1.resolve("4"));

        Path subdirectory2 = Files.createDirectory(testDirectory.resolve("dir2"));
        Files.createFile(subdirectory2.resolve("5"));
    }

    @AfterAll
    static void tearDown() throws IOException {
        try (Stream<Path> fileTree = Files.walk(testDirectory)) {
            fileTree.sorted(Comparator.reverseOrder())
                .forEach(file -> {
                        try {
                            Files.delete(file);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                );
        }
    }

    @Test
    @DisplayName("Количество директорий")
    void directoriesFindAmount() {
        int amount = 2;
        DirectorySearch fileListTask = new DirectorySearch(testDirectory, amount);
        DirectoryInfo result = fileListTask.compute();

        int actualResult = result.paths().size();
        assertThat(actualResult).isEqualTo(2);
    }
}
