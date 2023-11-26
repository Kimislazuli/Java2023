package edu.hw4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public final class AnimalUtils {
    private AnimalUtils() {
    }

    // Task 1
    public static List<Animal> sortAnimalsByHeightAscending(List<Animal> animals) {
        return animals
            .stream()
            .sorted(Comparator
                .comparing(Animal::height)
            )
            .toList();
    }

    // Task 2
    public static List<Animal> sortAnimalsByWeightDescending(List<Animal> animals) {
        return animals
            .stream()
            .sorted(Comparator
                .comparing(Animal::weight)
                .reversed())
            .toList();
    }

    // Task 3
    public static Map<Animal.Type, Integer> howManyAnimalsOfEveryType(List<Animal> animals) {
        return animals
            .stream()
            .collect(Collectors
                .groupingBy(
                    Animal::type,
                    Collectors.summingInt(x -> 1)
                )
            );
    }

    // Task 4
    public static Animal whoseNameIsTheLongest(List<Animal> animals) {
        return animals
            .stream()
            .max(Comparator
                .comparing((Animal animal) -> animal.name().length())
            )
            .orElseThrow();
    }

    // Task 5
    public static Animal.Sex femaleOrMaleMore(List<Animal> animals) {
        return animals
            .stream()
            .collect(Collectors
                .groupingBy(
                    Animal::sex,
                    Collectors.counting()
                ))
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElseThrow();
    }

    // Task 6
    public static Map<Animal.Type, Animal> theHeaviestAnimalOfEachType(List<Animal> animals) {
        return animals
            .stream()
            .collect(Collectors
                .groupingBy(
                    Animal::type,
                    Collectors.maxBy(Comparator.comparing(Animal::weight))
                )
            )
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    e -> e.getValue().orElseThrow()
                )
            );
    }

    // Task 7
    public static Animal theOldestAnimalNumberN(List<Animal> animals, int number) {
        return animals
            .stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .skip(number - 1)
            .findFirst()
            .orElseThrow();
    }

    // Task 8
    public static Animal theHeaviestAmongLowerThanN(List<Animal> animals, int height) {
        return animals
            .stream()
            .filter(s -> s.height() < height)
            .max(Comparator.comparing(Animal::weight))
            .orElseThrow();
    }

    // Task 9
    public static Integer amountOfPaws(List<Animal> animals) {
        return animals
            .stream()
            .map(Animal::paws)
            .reduce(0, Integer::sum);
    }

    // Task 10
    public static List<Animal> ageNotEqualsToPawsAmount(List<Animal> animals) {
        return animals
            .stream()
            .filter(s -> s.paws() != s.age())
            .toList();
    }

    // Task 11
    @SuppressWarnings("checkstyle:MagicNumber")
    public static List<Animal> hugeAndCanBite(List<Animal> animals) {
        return animals
            .stream()
            .filter(s -> s.height() > 100 && s.bites())
            .toList();
    }

    // Task 12
    public static Integer animalsWithWeightGreaterThanHeight(List<Animal> animals) {
        return animals
            .stream()
            .filter(s -> s.weight() > s.height())
            .mapToInt(x -> 1).sum();
    }

    // Task 13
    public static List<Animal> animalsWithNamesOfTwoOrMoreWords(List<Animal> animals) {
        return animals
            .stream()
            .filter(s -> s.name().split(" ").length > 1)
            .toList();
    }

    // Task 14
    public static Boolean isThereDogHigherThanKCentimetres(List<Animal> animals, int height) {
        return animals
            .stream()
            .anyMatch(s -> s.type() == Animal.Type.DOG && s.height() > height);
    }

    // Task 15
    public static Map<Animal.Type, Integer> summWeightOfEachTypeOfAnimalsInAgeFromKToL(
        List<Animal> animals,
        int startAgePoint,
        int endAgePoint
    ) {
        return animals
            .stream()
            .filter(x -> x.age() >= startAgePoint && x.age() <= endAgePoint)
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.summingInt(Animal::weight)
                )
            );
    }

    // Task 16
    public static List<Animal> sortAnimalsByTypeSexAndName(List<Animal> animals) {
        return animals
            .stream()
            .sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name))
            .toList();
    }

    // Task 17
    public static Boolean isItTrueThatSpidersBiteMoreOftenThanDog(List<Animal> animals) {
        Map<Animal.Type, Double> animalBitesPercentage = new HashMap<>() {{
            animals
                .stream()
                .filter(a -> a.type() == Animal.Type.SPIDER || a.type() == Animal.Type.DOG)
                .collect(Collectors.groupingBy(
                        Animal::type,
                        Collectors.counting()
                    )
                )
                .forEach(
                    (k, v) -> {
                        if (k == Animal.Type.SPIDER) {
                            put(
                                k,
                                (double) animals
                                    .stream()
                                    .filter(a -> a.type() == Animal.Type.SPIDER).filter(Animal::bites).count() / v
                            );
                        }
                        if (k == Animal.Type.DOG) {
                            put(
                                k,
                                (double) animals.stream()
                                    .filter(a -> a.type() == Animal.Type.DOG).filter(Animal::bites).count() / v
                            );
                        }
                    }
                );
        }};

        if (animalBitesPercentage.containsKey(Animal.Type.DOG)
            && animalBitesPercentage.containsKey(Animal.Type.SPIDER)) {
            return animalBitesPercentage.get(Animal.Type.DOG) < animalBitesPercentage.get(Animal.Type.SPIDER);
        }
        return false;
    }

    // Task 18
    public static Animal theHeaviestFishInSeveralLists(List<List<Animal>> animals) {
        return animals
            .stream()
            .flatMap(List::stream)
            .filter(a -> a.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .orElseThrow(
                () -> new NoSuchElementException("There's no fish in these lists")
            );
    }

    // Task 19
    public static Map<String, Set<ValidationError>> notValidAnimalsWithListOfErrors(List<Animal> animals) {
        return animals
            .stream()
            .collect(
                Collectors.toMap(
                    Animal::name,
                    Animal::checkAnimalValidationErrors
                )
            ).entrySet()
            .stream()
            .filter(s -> !s.getValue().isEmpty())
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue
                )
            );
    }

    // Task 20
    public static Map<String, String> prettierNotValidAnimalsWithListOfErrors(List<Animal> animals) {
        return new HashMap<>() {
            {
                notValidAnimalsWithListOfErrors(animals)
                    .forEach(
                        (k, v) ->
                            put(k, String.join(", ", v.stream().map(e -> e.field().toString()).sorted().toList()))
                    );
            }
        };
    }
}
