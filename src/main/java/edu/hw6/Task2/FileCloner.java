package edu.hw6.Task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class FileCloner {
    private FileCloner() {
    }

    private static final String KEY_NAME = "name";
    private static final String KEY_EXTENSION = "extension";

    public static void cloneFile(Path path) throws IOException {
        Path newPath = path;
        if (Files.exists(newPath)) {
            Path copyPath = createNemPath(newPath.getParent(), parseFilename(newPath), " — копия");
            newPath = copyPath;
            int counter = 2;
            while (Files.exists(newPath)) {
                newPath = createNemPath(copyPath.getParent(), parseFilename(copyPath), String.format(" (%d)", counter));
                counter++;
            }
        }
        Files.createFile(newPath);
    }

    private static Map<String, String> parseFilename(Path path) {
        HashMap<String, String> result = new HashMap<>();
        String[] separatedFilename = path.getFileName().toString().split("\\.");
        String filename = String.join(".", Arrays.copyOfRange(separatedFilename, 0, separatedFilename.length - 1));
        result.put(KEY_NAME, filename.toString());
        result.put(KEY_EXTENSION, separatedFilename[separatedFilename.length - 1]);
        return result;
    }

    private static Path createNemPath(Path parent, Map<String, String> filename, String other) {
        StringBuilder filenameStringBuilder = new StringBuilder(filename.get(KEY_NAME) + other);
        if (filename.containsKey(KEY_EXTENSION)) {
            filenameStringBuilder.append("." + filename.get(KEY_EXTENSION));
        }
        return Path.of(parent + File.separator + filenameStringBuilder);
    }
}
