package edu.hw6.Task1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private static final Logger LOGGER = LogManager.getLogger(DiskMap.class);
    private final Path mapDirectory;
    private int size;

    public enum OpenFileMarker {
        OPEN_OLD,
        CREATE_NEW
    }

    @SuppressWarnings("checkstyle:MissingSwitchDefault")
    public DiskMap(Path path, OpenFileMarker marker) {
        mapDirectory = path;
        switch (marker) {
            case OPEN_OLD -> openOldMap();
            case CREATE_NEW -> createNewMap();
        }
    }

    private void openOldMap() {
        if (Files.notExists(mapDirectory)) {
            throw new IllegalArgumentException("You can't reopen file that doesn't exists");
        }
        try (Stream<Path> stream = Files.list(mapDirectory)) {
            size = stream.map(e -> 1).reduce(0, Integer::sum);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void createNewMap() {
        size = 0;
        if (Files.exists(mapDirectory)) {
            deleteMap();
        }
        try {
            Files.createDirectories(mapDirectory);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return Files.exists(Path.of(mapDirectory + File.separator + key));
    }

    @Override
    public boolean containsValue(Object value) {
        Collection<String> values = values();
        return values.contains(value);
    }

    @Override
    public String get(Object key) {
        Path pathToKey = Path.of(mapDirectory + File.separator + key);
        if (Files.notExists(pathToKey)) {
            return null;
        } else {
            try {
                return new String(Files.readAllBytes(pathToKey));
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        Path pathToKey = Path.of(mapDirectory + File.separator + key);
        String oldValue = null;
        if (Files.notExists(pathToKey)) {
            try {
                Files.createFile(pathToKey);
                size++;
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        } else {
            try {
                oldValue = new String(Files.readAllBytes(pathToKey));
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        try {
            Files.write(pathToKey, value.getBytes());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return oldValue;
    }

    @Override
    public String remove(Object key) {
        Path pathToKey = Path.of(mapDirectory + File.separator + key);
        if (Files.notExists(pathToKey)) {
            return null;
        } else {
            try {
                String value = new String(Files.readAllBytes(pathToKey));
                Files.delete(pathToKey);
                size--;
                return value;
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (Entry<? extends String, ? extends String> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        createNewMap();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        try (Stream<Path> filesList = Files.list(mapDirectory)) {
            return filesList
                .map(p -> p.getFileName().toString())
                .collect(Collectors.toSet());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return Collections.emptySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        try (Stream<Path> filesList = Files.list(mapDirectory)) {
            return filesList
                .map(p -> get(p.getFileName().toString()))
                .toList();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        try (Stream<Path> filesList = Files.list(mapDirectory)) {
            return filesList
                .map(p -> new DiskMapEntry(p.getFileName().toString(), get(p.getFileName().toString())))
                .collect(Collectors.toSet());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return Collections.emptySet();
    }

    public void deleteMap() {
        try (Stream<Path> fileTree = Files.walk(mapDirectory)) {
            fileTree.sorted(Comparator.reverseOrder())
                .forEach(file -> {
                        try {
                            Files.delete(file);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                );
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Map<String, String> loadAsHashmap() {
        Map<String, String> map = new HashMap<>();
        for (var entry : entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    static class DiskMapEntry implements Map.Entry<String, String> {
        final String key;
        String value;

        DiskMapEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public String setValue(String value) {
            String oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}
