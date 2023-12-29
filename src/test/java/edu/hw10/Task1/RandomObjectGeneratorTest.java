package edu.hw10.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomObjectGeneratorTest {
    RandomObjectGenerator rog = new RandomObjectGenerator();
    @Test
    @DisplayName("Генерация POJO через конструктор")
    void generatePojoViaConstructor() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Person actualResult = rog.nextObject(Person.class);

        assertThat(actualResult.getName()).isNull();
    }

    @Test
    @DisplayName("Генерация record через конструктор")
    void generateRecordViaConstructor() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Object actualResult = rog.nextObject(PersonRecord.class);

        assertThat(actualResult).isInstanceOf(PersonRecord.class);
    }

    @Test
    @DisplayName("Генерация POJO через фабричный метод")
    void generatePojoViaFabricMethod() throws InvocationTargetException, IllegalAccessException {
        Object actualResult = rog.nextObject(Person.class, "create");

        assertThat(actualResult).isInstanceOf(Person.class);
    }

    @Test
    @DisplayName("Несущетсвующий фабричный метод")
    void wrongFabricMethod() throws InvocationTargetException, IllegalAccessException {
        assertThrows(IllegalArgumentException.class,
            () -> rog.nextObject(Person.class, "crt"));
    }

    @Test
    @DisplayName("Аннотированный класс")
    void annotatedClassConstructor() throws InvocationTargetException, IllegalAccessException, InstantiationException {
        AnnotatedPerson actualResult = rog.nextObject(AnnotatedPerson.class);

        assertThat(actualResult.getAge()).isBetween(0, 100);
        assertThat(actualResult.getName()).isNotNull();
    }
}
