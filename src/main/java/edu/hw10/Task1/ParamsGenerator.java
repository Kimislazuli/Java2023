package edu.hw10.Task1;

import edu.hw10.Task1.annotations.Max;
import edu.hw10.Task1.annotations.Min;
import edu.hw10.Task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.security.SecureRandom;
import java.util.Random;

public final class ParamsGenerator {
    private static final int MAX_STRING_LEN = 30;

    private ParamsGenerator() {
    }

    public static Object[] generateParams(Parameter[] params) {
        Object[] processedParameters = new Object[params.length];

        for (int i = 0; i < params.length; i++) {
            Parameter parameter = params[i];
            Annotation[] annotations = parameter.getAnnotations();
            if (parameter.getType() == String.class) {
                processedParameters[i] = getNewStringValue(annotations);
            } else if (parameter.getType() == int.class) {
                processedParameters[i] = getNewIntValue(annotations);
            } else {
                throw new IllegalArgumentException("Неподдерживаемый тип параметра");
            }
        }
        return processedParameters;
    }

    private static final Random RANDOM = new SecureRandom();

    private static int getNewIntValue(Annotation[] paramAnnotations) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        for (var annotation : paramAnnotations) {
            if (annotation.annotationType() == Max.class) {
                Max maxAnnotation = (Max) annotation;
                max = maxAnnotation.value();
            } else if (annotation.annotationType() == Min.class) {
                Min minAnnotation = (Min) annotation;
                min = minAnnotation.value();
            }
        }
        return RANDOM.nextInt(min, max);
    }

    private static String getNewStringValue(Annotation[] paramAnnotations) {
        String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("");
        String result = null;
        for (var annotation : paramAnnotations) {
            if (annotation.annotationType() == NotNull.class) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < RANDOM.nextInt(0, MAX_STRING_LEN); i++) {
                    builder.append(alphabet[RANDOM.nextInt(0, alphabet.length - 1)]);
                }
                result = builder.toString();
            }
        }
        return result;
    }
}
