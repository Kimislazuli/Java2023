package edu.hw8.Task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractPasswordGuesser {
    protected Map<String, String> passwords;
    protected Map<String, String> result;
    protected static final int MAX_LEN = 6;
    protected static final int SLEEP = 10;
    protected static final Logger LOGGER = LogManager.getLogger(AbstractPasswordGuesser.class);

    protected static final String[] ALPHABET =
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".split("");
    protected static final int ALPHABET_LENGTH = 62;

    public AbstractPasswordGuesser(String filename) {
        try {
            passwords = readFile(filename);
            result = new HashMap<>();
        } catch (IOException e) {
            LOGGER.error("Не могу прочитать файл");
        }
    }

    public abstract void findAllPasswords();

    private Map<String, String> readFile(String filename) throws IOException {
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            throw new NoSuchFileException("Файл не сущестует");
        }
        return Files
            .lines(path)
            .map(l -> l.split("\s+"))
            .collect(Collectors.toMap(a -> a[1], a -> a[0]));
    }

    public Map<String, String> getResult() {
        return result;
    }

    public Map<String, String> getPasswords() {
        return passwords;
    }
}
