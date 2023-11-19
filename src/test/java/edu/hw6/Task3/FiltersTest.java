package edu.hw6.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import static edu.hw6.Task3.Filters.globMatches;
import static edu.hw6.Task3.Filters.largerThan;
import static edu.hw6.Task3.Filters.magicNumber;
import static edu.hw6.Task3.Filters.readable;
import static edu.hw6.Task3.Filters.regexContains;
import static edu.hw6.Task3.Filters.regularFile;
import static org.assertj.core.api.Assertions.assertThat;

public class FiltersTest {
    Path directory = Path.of(
        "src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator +
            "Task3");

    @Test
    @DisplayName("Файл не является директорией")
    void isRegularTest() {
        DirectoryStream.Filter<Path> filter = regularFile();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(directory, filter)) {
            List<Path> actualResult = new ArrayList<>();
            entries.forEach(actualResult::add);
            List<Path> expectedResult = Arrays.asList(
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "2.txt"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "a.png"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "image(3).png"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "jdk_lektsiia2-3170076c-7887-4f63-aece-060895df7d8d.pdf"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "lektsiia_2-3c1c4685-7846-4078-8128-f17eeb12fe57(1).pptx"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "log4j2.properties"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "png-clipart-sign-stop-sign-stop.png")
            );
            assertThat(actualResult).containsExactlyInAnyOrderElementsOf(expectedResult);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Файл читаемый")
    void isReadableTest() {
        DirectoryStream.Filter<Path> filter = readable();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(directory, filter)) {
            List<Path> actualResult = new ArrayList<>();
            entries.forEach(actualResult::add);
            List<Path> expectedResult = Arrays.asList(
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "2.txt"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "directory"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "a.png"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "image(3).png"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "jdk_lektsiia2-3170076c-7887-4f63-aece-060895df7d8d.pdf"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "lektsiia_2-3c1c4685-7846-4078-8128-f17eeb12fe57(1).pptx"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "log4j2.properties"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "png-clipart-sign-stop-sign-stop.png")
            );
            assertThat(actualResult).containsExactlyInAnyOrderElementsOf(expectedResult);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Файл больше заданного размера")
    void isLargerThanTest() {
        DirectoryStream.Filter<Path> filter = largerThan(100_000);

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(directory, filter)) {
            List<Path> actualResult = new ArrayList<>();
            entries.forEach(actualResult::add);
            List<Path> expectedResult = Arrays.asList(
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "jdk_lektsiia2-3170076c-7887-4f63-aece-060895df7d8d.pdf"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "lektsiia_2-3c1c4685-7846-4078-8128-f17eeb12fe57(1).pptx")
            );
            assertThat(actualResult).containsExactlyInAnyOrderElementsOf(expectedResult);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Имеет определённый хедер")
    void matchesFormat() {
        DirectoryStream.Filter<Path> filter = magicNumber(0x89, 'P', 'N', 'G');

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(directory, filter)) {
            List<Path> actualResult = new ArrayList<>();
            entries.forEach(actualResult::add);
            List<Path> expectedResult = Arrays.asList(
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "image(3).png"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "png-clipart-sign-stop-sign-stop.png")
            );
            assertThat(actualResult).containsExactlyInAnyOrderElementsOf(expectedResult);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Соответствует глобу")
    void matchesGlob() {
        DirectoryStream.Filter<Path> filter = globMatches("*a.png");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(directory, filter)) {
            List<Path> actualResult = new ArrayList<>();
            entries.forEach(actualResult::add);
            List<Path> expectedResult = Arrays.asList(
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "a.png")
            );
            assertThat(actualResult).containsExactlyInAnyOrderElementsOf(expectedResult);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Содержит регекс")
    void containsRegex() {
        DirectoryStream.Filter<Path> filter = regexContains("[-]");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(directory, filter)) {
            List<Path> actualResult = new ArrayList<>();
            entries.forEach(actualResult::add);
            List<Path> expectedResult = Arrays.asList(
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "jdk_lektsiia2-3170076c-7887-4f63-aece-060895df7d8d.pdf"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "lektsiia_2-3c1c4685-7846-4078-8128-f17eeb12fe57(1).pptx"),
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "png-clipart-sign-stop-sign-stop.png")
            );
            assertThat(actualResult).containsExactlyInAnyOrderElementsOf(expectedResult);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Несколько фильтров")
    void composition() {
        DirectoryStream.Filter<Path> filter = regularFile()
            .and(readable())
            .and(largerThan(100))
            .and(magicNumber(0x89, 'P', 'N', 'G'))
            .and(globMatches("*.png"))
            .and(regexContains("[(3)}]"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(directory, filter)) {
            List<Path> actualResult = new ArrayList<>();
            entries.forEach(actualResult::add);
            List<Path> expectedResult = Arrays.asList(
                Path.of("src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator + "Task3" + File.separator + "image(3).png")
            );
            assertThat(actualResult).containsExactlyInAnyOrderElementsOf(expectedResult);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
