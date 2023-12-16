package edu.hw10.Task2.caches;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class DiskCache extends AbstractCache {
    private final Path mapDirectory;
    private int size = 0;

    public DiskCache(Path mapDirectory) throws IOException {
        this.mapDirectory = mapDirectory;
        if (Files.exists(mapDirectory)) {
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
                throw new RuntimeException(e);
            }
        }

        Files.createDirectory(mapDirectory);
    }

    @Override
    public Object get(String key) {
        if (size == 0) {
            return null;
        }

        Path pathToKey = Paths.get(mapDirectory.toString(), key);
        if (Files.notExists(pathToKey)) {
            return null;
        } else {
            try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Path.of(key)))) {
                return in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void put(String key, Object value) {
        Path pathToKey = Paths.get(mapDirectory.toString(), key);
        if (Files.notExists(pathToKey)) {
            try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Path.of(key)))) {
                Files.createFile(pathToKey);
                size++;
                out.writeObject(value);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
