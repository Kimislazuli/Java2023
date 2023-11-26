package edu.hw4;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("checkstyle:MagicNumber")
public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> 4;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> 8;
        };
    }

    public Set<ValidationError> checkAnimalValidationErrors() {
        Set<ValidationError> errors = new HashSet<>();
        if (!nameIsValid()) {
            errors.add(new ValidationError(ValidationError.Field.NAME));
        }

        if (!typeIsValid()) {
            errors.add(new ValidationError(ValidationError.Field.TYPE));
        }

        if (!sexIsValid()) {
            errors.add(new ValidationError(ValidationError.Field.SEX));
        }

        if (!heightIsValid()) {
            errors.add(new ValidationError(ValidationError.Field.HEIGHT));
        }

        if (!weightIsValid()) {
            errors.add(new ValidationError(ValidationError.Field.WEIGHT));
        }

        if (!ageIsValid()) {
            errors.add(new ValidationError(ValidationError.Field.AGE));
        }
        return errors;
    }

    private boolean nameIsValid() {
        return name != null && name.length() > 0;
    }

    private boolean typeIsValid() {
        return type != null;
    }

    private boolean sexIsValid() {
        return sex != null;
    }

    private boolean ageIsValid() {
        return age >= 0 && age < 100;
    }

    private boolean heightIsValid() {
        return height >= 0 && height <= 200;
    }

    private boolean weightIsValid() {
        return weight >= 0 && weight <= 200;
    }
}

