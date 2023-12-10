package edu.hw9.Task2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import edu.hw9.Task2.file.FileSearch;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FileSearchTest {
    static Path testDirectory;

    @BeforeAll
    static void setUp() throws IOException {
        testDirectory = Files.createTempDirectory("test");

        Files.createFile(testDirectory.resolve("empty1.txt"));
        Files.createFile(testDirectory.resolve("empty2.txt"));
        Files.createFile(testDirectory.resolve("picture.png"));
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
    void findFilesMatchingPredicate() {
        Predicate<Path> predicate = path -> path.toString().toLowerCase().endsWith(".txt");

        FileSearch fileSearchTask = new FileSearch(testDirectory, predicate);
        List<Path> actualResult = fileSearchTask.compute();

        assertThat(actualResult).containsExactlyInAnyOrderElementsOf(List.of(
                testDirectory.resolve("empty1.txt"),
                testDirectory.resolve("empty2.txt")
            )
        );
    }
}
