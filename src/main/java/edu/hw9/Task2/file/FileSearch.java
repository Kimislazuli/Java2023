package edu.hw9.Task2.file;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class FileSearch extends RecursiveTask<List<Path>> {
    private final Path path;
    private final Predicate<Path> predicate;

    public FileSearch(Path path, Predicate<Path> predicate) {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Файла не существует.");
        }
        this.path = path;
        this.predicate = predicate;
    }

    @Override public List<Path> compute() {
        List<Path> resultPaths = new ArrayList<>();

        if (Files.isDirectory(path)) {
            List<FileSearch> subtasks = new ArrayList<>();
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                for (Path entry : stream) {
                    FileSearch subtask = new FileSearch(entry, predicate);
                    subtasks.add(subtask);
                    subtask.fork();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            for (FileSearch subtask : subtasks) {
                List<Path> subResult = subtask.join();
                resultPaths.addAll(subResult);
            }
        } else {
            if (predicate.test(path)) {
                resultPaths.add(path);
            }
        }

        return resultPaths;
    }
}
