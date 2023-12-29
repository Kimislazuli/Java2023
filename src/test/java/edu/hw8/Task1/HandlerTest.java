package edu.hw8.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class HandlerTest {
    private static final Map<String, String> ANSWERS;

    static {
        ANSWERS = new HashMap<>();
        ANSWERS.put("личности", "Не переходи на личности там, где их нет");
        ANSWERS.put("оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");
        ANSWERS.put("глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.");
        ANSWERS.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");
    }

    private static final String DEFAULT_ANSWER = "Я даже не знаю, что на это ответить...";

    @Test
    @DisplayName("Корректный вывод для пустой строки")
    void emptyStringInput() {
        ClientHandler clientHandler = new ClientHandler(null) {
            @Override
            public void run() {
                String request = "";
                String answer = ANSWERS.getOrDefault(request.toLowerCase(), DEFAULT_ANSWER);
                assertThat(answer).isEqualTo(DEFAULT_ANSWER);
            }
        };
        clientHandler.run();
    }

    @Test
    @DisplayName("Корректный вывод для неизвестной строки")
    void unknownStringInput() {
        ClientHandler clientHandler = new ClientHandler(null) {
            @Override
            public void run() {
                String request = "Привет";
                String answer = ANSWERS.getOrDefault(request.toLowerCase(), DEFAULT_ANSWER);
                assertThat(answer).isEqualTo(DEFAULT_ANSWER);
            }
        };
        clientHandler.run();
    }

    @Test
    @DisplayName("Корректный вывод для известной строки")
    void knownStringInput() {
        ClientHandler clientHandler = new ClientHandler(null) {
            @Override
            public void run() {
                String request = "ИНТЕЛЛЕКТ";
                String answer = ANSWERS.getOrDefault(request.toLowerCase(), DEFAULT_ANSWER);
                assertThat(answer).isEqualTo("Чем ниже интеллект, тем громче оскорбления");
            }
        };
        clientHandler.run();
    }
}
