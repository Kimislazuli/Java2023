package edu.hw9.Task2.directory;

import java.nio.file.Path;
import java.util.List;

public record DirectoryInfo(int filesAmount, List<Path> paths) {
}
