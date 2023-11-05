package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import static edu.hw4.AnimalUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnimalUtilsTest {
    @Test
    @DisplayName("Сортировка по возрастанию роста")
    void correctlySortAnimalsByHeightAscending() {
        List<Animal> actualResult = sortAnimalsByHeightAscending(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false),
            new Animal("Seb", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 2, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 2, 1, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        ));

        assertThat(actualResult).containsExactly(
            new Animal("Seb", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 2, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 2, 1, false),
            new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true),
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true)
        );
    }

    @Test
    @DisplayName("Сортировка по возрастанию роста, пустой список")
    void correctlySortAnimalsByHeightAscendingEmptyList() {
        assertThat(Collections.emptyList()).containsExactly();
    }

    @Test
    @DisplayName("Сортировка по убыванию веса")
    void correctlySortAnimalsByWeightDescending() {
        List<Animal> actualResult = sortAnimalsByWeightDescending(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false),
            new Animal("Seb", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 2, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 2, 1, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        ));

        assertThat(actualResult).containsExactly(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true),
            new Animal("Seb", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 2, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 2, 1, false)
        );
    }

    @Test
    @DisplayName("Количество животных каждого вида")
    void correctlyCountHowManyAnimalsOfEveryType() {
        Map<Animal.Type, Integer> actualResult = howManyAnimalsOfEveryType(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false),
            new Animal("Seb", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 2, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 2, 1, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        ));
        Map<Animal.Type, Integer> expectedResult = new HashMap<>();
        expectedResult.put(Animal.Type.CAT, 2);
        expectedResult.put(Animal.Type.DOG, 1);
        expectedResult.put(Animal.Type.SPIDER, 1);
        expectedResult.put(Animal.Type.FISH, 1);

        assertThat(actualResult).containsExactlyInAnyOrderEntriesOf(expectedResult);
    }

    @Test
    @DisplayName("Животное с самым длинным именем")
    void correctlyWhoseNameIsTheLongest() {
        Animal actualResult = whoseNameIsTheLongest(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false),
            new Animal("Seb", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 2, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 2, 1, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        ));

        assertThat(actualResult).isEqualTo(new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true));
    }

    @Test
    @DisplayName("Животное с самым длинным именем, если несколько имён одной длины")
    void correctlyWhoseNameIsTheLongestIfManyNamesWithSameLength() {
        Animal actualResult = whoseNameIsTheLongest(Arrays.asList(
            new Animal("Ann", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Seb", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 2, true),
            new Animal("Sam", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        ));

        assertThat(actualResult).isEqualTo(new Animal("Ann", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true));
    }

    @Test
    @DisplayName("Какого пола животных больше")
    void correctlyCountFemaleOrMaleMore() {
        Animal.Sex actualResult = femaleOrMaleMore(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false),
            new Animal("Seb", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 2, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 2, 1, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        ));

        assertThat(actualResult).isEqualTo(Animal.Sex.M);
    }

    @Test
    @DisplayName("Какого пола животных больше, если количество одинаковое")
    void correctlyCountFemaleOrMaleMoreIfEquals() {
        Animal.Sex actualResult = femaleOrMaleMore(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 2, 1, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        ));

        assertThat(actualResult).isEqualTo(Animal.Sex.M);
    }

    @Test
    @DisplayName("Поиск самого тяжелого животного каждого вида")
    void correctlyFindTheHeaviestAnimalOfEachType() {
        Map<Animal.Type, Animal> actualResult = theHeaviestAnimalOfEachType(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Henry", Animal.Type.BIRD, Animal.Sex.M, 5, 16, 1, false),
            new Animal("Seb", Animal.Type.FISH, Animal.Sex.M, 2, 5, 10, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 2, 1, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        ));

        Map<Animal.Type, Animal> expectedResult = new HashMap<>();
        expectedResult.put(Animal.Type.CAT, new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true));
        expectedResult.put(Animal.Type.BIRD, new Animal("Henry", Animal.Type.BIRD, Animal.Sex.M, 5, 16, 1, false));
        expectedResult.put(Animal.Type.FISH, new Animal("Seb", Animal.Type.FISH, Animal.Sex.M, 2, 5, 10, true));

        assertThat(actualResult).containsExactlyInAnyOrderEntriesOf(expectedResult);
    }

    @Test
    @DisplayName("Поиск самого старого n-го животного")
    void correctlyFindTheOldestAnimalNumberN() {
        Animal actualResult = theOldestAnimalNumberN(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Henry", Animal.Type.BIRD, Animal.Sex.M, 5, 16, 1, false),
            new Animal("Seb", Animal.Type.FISH, Animal.Sex.M, 2, 5, 10, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 2, 1, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        ), 4);

        assertThat(actualResult).isEqualTo(new Animal("Seb", Animal.Type.FISH, Animal.Sex.M, 2, 5, 10, true));
    }

    @Test
    @DisplayName("Поиск самого старого n-го животного при n > количества животных")
    void lessThanNFindTheOldestAnimalNumberN() {
        assertThrows(
            Exception.class,
            () -> {
                theOldestAnimalNumberN(Arrays.asList(
                    new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
                    new Animal("Henry", Animal.Type.BIRD, Animal.Sex.M, 5, 16, 1, false),
                    new Animal("Seb", Animal.Type.FISH, Animal.Sex.M, 2, 5, 10, true),
                    new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 2, 1, false),
                    new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
                ), 10);
            }
        );
    }

    @Test
    @DisplayName("Самое тяжелое животное среди животных ниже n сантиметров")
    void correctlyFindTheHeaviestAmongLowerThanN() {
        Animal actualResult = theHeaviestAmongLowerThanN(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false),
            new Animal("Seb", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 2, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 2, 1, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        ), 20);

        assertThat(actualResult).isEqualTo(new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false));
    }

    @Test
    @DisplayName("Самое тяжелое животное среди животных ниже n сантиметровб если таких нет")
    void noLowerThanNFindTheHeaviestAmongLowerThanN() {
        assertThrows(
            NoSuchElementException.class,
            () -> {
                theHeaviestAmongLowerThanN(Arrays.asList(
                    new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
                    new Animal("Henry", Animal.Type.BIRD, Animal.Sex.M, 5, 16, 1, false),
                    new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
                ), 10);
            }
        );
    }

    @Test
    @DisplayName("Общее количество лап")
    void correctlyCountAmountOfPaws() {
        Integer actualResult = amountOfPaws(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false),
            new Animal("Seb", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 2, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 2, 1, false),
            new Animal("Mortimer", Animal.Type.BIRD, Animal.Sex.M, 10, 25, 5, true)
        ));

        assertThat(actualResult).isEqualTo(18);
    }

    @Test
    @DisplayName("Список животных, у которых возраст не совпадает с количеством лап")
    void correctlyCountAmountOfAnimalsWhichAgeNotEqualsToPawsAmount() {
        List<Animal> actualResult = ageNotEqualsToPawsAmount(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false),
            new Animal("Seb", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 2, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 2, 1, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        ));

        assertThat(actualResult).containsExactly(
            new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false),
            new Animal("Seb", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 2, true),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        );
    }

    @Test
    @DisplayName("Список животных выше 100 сантиметров, которые могут укусить")
    void correctlyFindAnimalsThatHugeAndCanBite() {
        List<Animal> actualResult = hugeAndCanBite(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 5, 100, 45, false),
            new Animal("Seb", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 2, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 2, 1, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        ));

        assertThat(actualResult).containsExactly(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true));
    }

    @Test
    @DisplayName("Количество животных, у которых вес превышает рост")
    void correctlyCountAnimalsWithWeightGreaterThanHeight() {
        Integer actualResult = animalsWithWeightGreaterThanHeight(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 100, 110, true),
            new Animal("Henry", Animal.Type.DOG, Animal.Sex.M, 5, 100, 45, false),
            new Animal("Seb", Animal.Type.SPIDER, Animal.Sex.M, 2, 1, 1, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 5, 6, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        ));

        assertThat(actualResult).isEqualTo(2);
    }

    @Test
    @DisplayName("Список животных с именами из 2 слов и более")
    void correctlyFindAnimalsWithNameOfTwoOrMoreWords() {
        List<Animal> actualResult = animalsWithNamesOfTwoOrMoreWords(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 100, 110, true),
            new Animal("Kristiane Hester", Animal.Type.DOG, Animal.Sex.M, 5, 100, 45, false),
            new Animal("Aleta Michele", Animal.Type.SPIDER, Animal.Sex.F, 2, 1, 1, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 5, 6, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        ));

        assertThat(actualResult).containsExactly(
            new Animal("Kristiane Hester", Animal.Type.DOG, Animal.Sex.M, 5, 100, 45, false),
            new Animal("Aleta Michele", Animal.Type.SPIDER, Animal.Sex.F, 2, 1, 1, true)
        );
    }

    @Test
    @DisplayName("Есть собака выше K сантиметров")
    void correctlyAnswerIsThereDogHigherThanKCentimetresTrue() {
        Boolean actualResult = isThereDogHigherThanKCentimetres(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 100, 110, true),
            new Animal("Kristiane Hester", Animal.Type.DOG, Animal.Sex.M, 5, 100, 45, false),
            new Animal("Aleta Michele", Animal.Type.SPIDER, Animal.Sex.F, 2, 1, 1, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 5, 6, false),
            new Animal("Mortimer", Animal.Type.DOG, Animal.Sex.M, 10, 25, 5, true)
        ), 25);

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Нет собак => нет собаки выше K сантиметров")
    void correctlyAnswerIsThereDogHigherThanKCentimetresNoDogs() {
        Boolean actualResult = isThereDogHigherThanKCentimetres(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 100, 110, true),
            new Animal("Aleta Michele", Animal.Type.SPIDER, Animal.Sex.F, 2, 1, 1, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 5, 6, false)
        ), 25);

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Нет собаки выше K сантиметров")
    void correctlyAnswerIsThereDogHigherThanKCentimetresFalse() {
        Boolean actualResult = isThereDogHigherThanKCentimetres(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 100, 110, true),
            new Animal("Kristiane Hester", Animal.Type.DOG, Animal.Sex.M, 5, 100, 45, false),
            new Animal("Aleta Michele", Animal.Type.SPIDER, Animal.Sex.F, 2, 1, 1, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 0, 5, 6, false),
            new Animal("Mortimer", Animal.Type.DOG, Animal.Sex.M, 10, 25, 5, true)
        ), 150);

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Суммарный вес животных каждого типа в возрасте от k до l лет")
    void correctlyCountSummWeightOfEachTypeOfAnimalsInAgeFromKToL() {
        Map<Animal.Type, Integer> actualResult = summWeightOfEachTypeOfAnimalsInAgeFromKToL(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 100, 110, true),
            new Animal("Kristiane Hester", Animal.Type.DOG, Animal.Sex.M, 5, 100, 45, false),
            new Animal("Aleta Michele", Animal.Type.FISH, Animal.Sex.F, 2, 1, 1, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 3, 5, 6, false),
            new Animal("Mortimer", Animal.Type.DOG, Animal.Sex.M, 10, 25, 5, true)
        ), 2, 9);

        Map<Animal.Type, Integer> expectedResult = new HashMap<>();
        expectedResult.put(Animal.Type.CAT, 110);
        expectedResult.put(Animal.Type.DOG, 45);
        expectedResult.put(Animal.Type.FISH, 7);

        assertThat(actualResult).containsExactlyInAnyOrderEntriesOf(expectedResult);
    }

    @Test
    @DisplayName("Сортировка животных по типу, полу и имени")
    void correctlySortAnimalsByTypeSexAndName() {
        List<Animal> actualResult = sortAnimalsByTypeSexAndName(Arrays.asList(
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 100, 110, true),
            new Animal("Kristiane Hester", Animal.Type.DOG, Animal.Sex.M, 5, 100, 45, false),
            new Animal("Aleta Michele", Animal.Type.FISH, Animal.Sex.F, 2, 1, 1, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 3, 5, 6, false),
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
        ));

        assertThat(actualResult).containsExactly(
            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true),
            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 100, 110, true),
            new Animal("Kristiane Hester", Animal.Type.DOG, Animal.Sex.M, 5, 100, 45, false),
            new Animal("Aleta Michele", Animal.Type.FISH, Animal.Sex.F, 2, 1, 1, true),
            new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 3, 5, 6, false)
        );
    }

    @Test
    @DisplayName("Самая тяжелая рыба среди нескольких списков")
    void correctlyFindTheHeaviestFishInSeveralLists() {
        Animal actualResult = theHeaviestFishInSeveralLists(
            Arrays.asList(
                Arrays.asList(
                    new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 100, 110, true),
                    new Animal("Kristiane Hester", Animal.Type.DOG, Animal.Sex.M, 5, 100, 45, false),
                    new Animal("Aleta Michele", Animal.Type.FISH, Animal.Sex.F, 2, 1, 1, true)
                ),
                Arrays.asList(
                    new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 3, 5, 6, false),
                    new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
                )
            )
        );

        assertThat(actualResult).isEqualTo(new Animal("Lucy", Animal.Type.FISH, Animal.Sex.F, 3, 5, 6, false));
    }

    @Test
    @DisplayName("Самая тяжелая рыба среди нескольких списков, но рыб нет")
    void correctlyFindTheHeaviestFishInSeveralListsWithoutFish() {
        assertThrows(
            NoSuchElementException.class,
            () -> {
                theHeaviestFishInSeveralLists(
                    Arrays.asList(
                        Arrays.asList(
                            new Animal("Susanne", Animal.Type.CAT, Animal.Sex.F, 4, 100, 110, true),
                            new Animal("Kristiane Hester", Animal.Type.DOG, Animal.Sex.M, 5, 100, 45, false)
                            ),
                        Arrays.asList(
                            new Animal("Mortimer", Animal.Type.CAT, Animal.Sex.M, 10, 25, 5, true)
                        )
                    )
                );
            }
        );
    }

    @Test
    @DisplayName("Определение списка ошибок валидации для каждого животного с ошибками")
    void correctlyFindNotValidAnimalsWithListOfErrors() {
        Map<String, Set<ValidationError>> actualResult = notValidAnimalsWithListOfErrors(
            Arrays.asList(
                new Animal("", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
                new Animal(null, Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false),
                new Animal("Seb", null, Animal.Sex.M, -2, 1, 2, true),
                new Animal("Lucy", Animal.Type.FISH, null, 0, -1, 10000, false),
                new Animal("Beatrise", Animal.Type.CAT, Animal.Sex.M, 10, 25, 2, true)
            )
        );

        Map<String, Set<ValidationError>> expectedResult = new HashMap<>();

        Set<ValidationError> nullsErrors = new HashSet<>();
        nullsErrors.add(new ValidationError(ValidationError.Field.NAME));
        expectedResult.put(null, nullsErrors);

        Set<ValidationError> emptyErrors = new HashSet<>();
        emptyErrors.add(new ValidationError(ValidationError.Field.NAME));
        expectedResult.put("", emptyErrors);

        Set<ValidationError> sebErrors = new HashSet<>();
        sebErrors.add(new ValidationError(ValidationError.Field.TYPE));
        sebErrors.add(new ValidationError(ValidationError.Field.AGE));
        expectedResult.put("Seb", sebErrors);

        Set<ValidationError> lucyErrors = new HashSet<>();
        lucyErrors.add(new ValidationError(ValidationError.Field.SEX));
        lucyErrors.add(new ValidationError(ValidationError.Field.HEIGHT));
        lucyErrors.add(new ValidationError(ValidationError.Field.WEIGHT));
        expectedResult.put("Lucy", lucyErrors);

        assertThat(actualResult).containsExactlyInAnyOrderEntriesOf(expectedResult);
    }

    @Test
    @DisplayName("Красивое форматирование списка ошибок валидации для каждого животного с ошибками")
    void correctlyFindNotValidAnimalsWithListOfErrorsPrettyFormatted() {
        Map<String, String> actualResult = prettierNotValidAnimalsWithListOfErrors(
            Arrays.asList(
                new Animal("", Animal.Type.CAT, Animal.Sex.F, 4, 120, 50, true),
                new Animal(null, Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false),
                new Animal("Seb", null, Animal.Sex.M, -2, 1, 2, true),
                new Animal("Lucy", Animal.Type.FISH, null, 0, -1, 10000, false),
                new Animal("Beatrise", Animal.Type.CAT, Animal.Sex.M, 10, 25, 2, true)
            )
        );

        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put(null, "NAME");
        expectedResult.put("", "NAME");
        expectedResult.put("Seb", "AGE, TYPE");
        expectedResult.put("Lucy", "HEIGHT, SEX, WEIGHT");

        assertThat(actualResult).containsExactlyInAnyOrderEntriesOf(expectedResult);
    }

    @Test
    @DisplayName("Кусаются ли пауки чаще собак? Да")
    void correctlyCheckIfSpidersBiteMoreOftenThanDogTrue() {
        Boolean actualResult = isItTrueThatSpidersBiteMoreOftenThanDog(Arrays.asList(
            new Animal("Hailey", Animal.Type.SPIDER, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Santos", Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, false),
            new Animal("Seb", Animal.Type.DOG, Animal.Sex.M, -2, 1, 2, true),
            new Animal("Lucy", Animal.Type.SPIDER, null, 0, -1, 10000, false),
            new Animal("Beth", Animal.Type.SPIDER, Animal.Sex.M, 10, 25, 2, true),
            new Animal("Andy", Animal.Type.SPIDER, Animal.Sex.F, 10, 25, 2, true)
        ));

        assertThat(actualResult).isTrue();
    }

    @Test
    @DisplayName("Кусаются ли пауки чаще собак? Нет")
    void correctlyCheckIfSpidersBiteMoreOftenThanDogFalse() {
        Boolean actualResult = isItTrueThatSpidersBiteMoreOftenThanDog(Arrays.asList(
            new Animal("Hailey", Animal.Type.SPIDER, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Santos", Animal.Type.DOG, Animal.Sex.M, 5, 16, 17, true),
            new Animal("Seb", Animal.Type.DOG, Animal.Sex.M, -2, 1, 2, true),
            new Animal("Lucy", Animal.Type.SPIDER, null, 0, -1, 10000, false),
            new Animal("Beth", Animal.Type.SPIDER, Animal.Sex.M, 10, 25, 2, true),
            new Animal("Andy", Animal.Type.SPIDER, Animal.Sex.F, 10, 25, 2, true)
        ));

        assertThat(actualResult).isFalse();
    }

    @Test
    @DisplayName("Кусаются ли пауки чаще собак? Недостаток информации")
    void correctlyCheckIfSpidersBiteMoreOftenThanDogNotEnoughInformation() {
        Boolean actualResult = isItTrueThatSpidersBiteMoreOftenThanDog(Arrays.asList(
            new Animal("Hailey", Animal.Type.SPIDER, Animal.Sex.F, 4, 120, 50, true),
            new Animal("Lucy", Animal.Type.SPIDER, null, 0, -1, 10000, false),
            new Animal("Beth", Animal.Type.SPIDER, Animal.Sex.M, 10, 25, 2, true),
            new Animal("Andy", Animal.Type.SPIDER, Animal.Sex.F, 10, 25, 2, true)
        ));

        assertThat(actualResult).isFalse();
    }
}
