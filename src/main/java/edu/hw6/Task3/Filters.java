package edu.hw6.Task3;

import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.PathMatcher;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Filters {
    private Filters() {
    }

    public static AbstractFilter regularFile() {
        return Files::isRegularFile;
    }

    public static AbstractFilter readable() {
        return Files::isReadable;
    }

    public static AbstractFilter largerThan(int size) {
        return path -> Files.size(path) >= size;
    }

    public static AbstractFilter magicNumber(int... numbers) {
        return path -> {
            if (Files.size(path) < numbers.length) {
                return false;
            }
            try (FileInputStream fileInputStream = new FileInputStream(path.toString());) {
                byte[] fileBytes = fileInputStream.readNBytes(numbers.length);
                for (int i = 0; i < fileBytes.length; i++) {
                    if (fileBytes[i] != (byte) numbers[i]) {
                        return false;
                    }
                }
            }
            return true;
        };
    }

    public static AbstractFilter globMatches(String glob) {
        return path -> {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
            return matcher.matches(path.getFileName());
        };
    }

    public static AbstractFilter regexContains(String regex) {
        return path -> {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(path.getFileName().toString());
            return matcher.find();
        };
    }
}
