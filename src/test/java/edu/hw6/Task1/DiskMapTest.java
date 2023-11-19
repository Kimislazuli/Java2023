package edu.hw6.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class DiskMapTest {
    Path path = Path.of(
        "src" + File.separator + "test" + File.separator + "resources" + File.separator + "hw6" + File.separator +  "Task1" + File.separator +  "diskmap");

    @Test
    @DisplayName("При первом добавлении нет старого значения")
    void firstTimePutReturnIsNull() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        String actualResult = diskMap.put("hello", "world");
        assertThat(actualResult).isNull();
    }

    @Test
    @DisplayName("При первом добавлении размер увеличился на единицу")
    void firstTimePutIncrementsSize() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        diskMap.put("hello", "world");
        int actualResult = diskMap.size();
        assertThat(actualResult).isEqualTo(1);
    }

    @Test
    @DisplayName("При первом добавлении значение корректно")
    void firstTimePutValueIsCorrect() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        diskMap.put("hello", "world");
        String actualResult = diskMap.get("hello");
        assertThat(actualResult).isEqualTo("world");
    }

    @Test
    @DisplayName("При повторном добавлении нет старого значения")
    void secondTimePutReturnIsNotNull() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        diskMap.put("hello", "world");
        String actualResult = diskMap.put("hello", "kitty");
        assertThat(actualResult).isEqualTo("world");
    }

    @Test
    @DisplayName("При повторном добавлении размер увеличился на единицу")
    void secondTimePutDoesntChangeSize() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        diskMap.put("hello", "world");
        diskMap.put("hello", "kitty");
        int actualResult = diskMap.size();
        assertThat(actualResult).isEqualTo(1);
    }

    @Test
    @DisplayName("При повторном добавлении значение корректно")
    void secondTimePutValueIsCorrect() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        diskMap.put("hello", "world");
        diskMap.put("hello", "kitty");
        String actualResult = diskMap.get("hello");
        assertThat(actualResult).isEqualTo("kitty");
    }

    @Test
    @DisplayName("Извлечение по несуществующему ключу")
    void getNonExistKey() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        String actualResult = diskMap.get("hello");
        assertThat(actualResult).isNull();
    }

    @Test
    @DisplayName("Извлечение по существующему ключу")
    void getExistKey() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        diskMap.put("hello", "kitty");
        String actualResult = diskMap.get("hello");
        assertThat(actualResult).isEqualTo("kitty");
    }

    @Test
    @DisplayName("Проверка несуществующего ключа")
    void containsNonExistKey() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        boolean actualResult = diskMap.containsKey("hello");
        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Проверка существующего ключа")
    void containsExistKey() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        diskMap.put("hello", "kitty");
        boolean actualResult = diskMap.containsKey("hello");
        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Проверка несуществующего значения")
    void containsNonExistValue() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        boolean actualResult = diskMap.containsValue("world");
        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Проверка существующего значения")
    void containsExistValue() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        diskMap.put("hello", "kitty");
        boolean actualResult = diskMap.containsValue("kitty");
        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Очистка")
    void clearMap() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        diskMap.put("first", "1");
        diskMap.put("second", "2");
        diskMap.clear();
        boolean actualResult = diskMap.isEmpty();
        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Расширение другим соответствием")
    void putAllCheck() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        diskMap.put("first", "1");
        diskMap.put("second", "2");

        Set<String> expectedKeySet = diskMap.keySet();

        Map<String, String> otherMap = new HashMap<>();
        otherMap.put("third", "3");
        otherMap.put("fourth", "4");
        expectedKeySet.addAll(otherMap.keySet());

        diskMap.putAll(otherMap);

        Set<String> actualKeySet = diskMap.keySet();
        assertThat(actualKeySet).containsExactlyInAnyOrderElementsOf(expectedKeySet);
    }

    @Test
    @DisplayName("Выгрузка соответствия")
    void loadExistingHashMap() {
        DiskMap diskMap = new DiskMap(path, DiskMap.OpenFileMarker.CREATE_NEW);
        diskMap.put("first", "1");
        diskMap.put("second", "2");

        DiskMap diskMapFromExisting = new DiskMap(path, DiskMap.OpenFileMarker.OPEN_OLD);

        Map<String, String> actualResult = diskMapFromExisting.loadAsHashmap();
        Map<String, String> expectedResult = diskMap.loadAsHashmap();

        assertThat(actualResult).containsExactlyInAnyOrderEntriesOf(expectedResult);
    }

    @Test
    @DisplayName("Проверка размера")
    void checkMapSize() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        diskMap.put("first", "1");
        diskMap.put("second", "2");
        int actualResult = diskMap.size();
        assertThat(actualResult).isEqualTo(2);
    }

    @Test
    @DisplayName("Проверка пустоты")
    void checkIsEmpty() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        boolean actualResult = diskMap.isEmpty();
        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Проверка непусоты")
    void checkNotEmpty() {
        DiskMap diskMap = new DiskMap(
            path,
            DiskMap.OpenFileMarker.CREATE_NEW
        );
        diskMap.put("first", "1");
        diskMap.put("second", "2");
        boolean actualResult = diskMap.isEmpty();
        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Удаление")
    void delete() {
        DiskMap diskMap = new DiskMap(path, DiskMap.OpenFileMarker.CREATE_NEW);
        diskMap.put("first", "1");
        diskMap.put("second", "2");
        diskMap.deleteMap();
        boolean actualResult = Files.exists(path);
        assertThat(actualResult).isFalse();
    }
}
