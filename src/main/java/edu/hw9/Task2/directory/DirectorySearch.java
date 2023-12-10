package edu.hw9.Task2.directory;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectorySearch extends RecursiveTask<DirectoryInfo> {
    private final Path path;
    private final int amount;

    public DirectorySearch(Path path, int amount) {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Файла не существует.");
        }
        this.path = path;
        this.amount = amount;
    }

    @Override public DirectoryInfo compute() {
        List<Path> currentPaths = new ArrayList<>();

        if (Files.isDirectory(path)) {
            List<DirectorySearch> subtasks = new ArrayList<>();
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                for (Path entry : stream) {
                    DirectorySearch subtask = new DirectorySearch(entry, amount);
                    subtasks.add(subtask);
                    subtask.fork();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            int currentFileAmount = 1;
            for (DirectorySearch subtask : subtasks) {
                DirectoryInfo subResult = subtask.join();
                currentFileAmount += subResult.filesAmount();
                currentPaths.addAll(subResult.paths());
            }

            if (currentFileAmount > amount) {
                currentPaths.add(path);
            }

            return new DirectoryInfo(currentFileAmount, currentPaths);
        } else {
            return new DirectoryInfo(1, currentPaths);
        }
    }
}
