package edu.hw6.Task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.net.URISyntaxException;
import static edu.hw6.Task5.HackerNews.hackerNewsTopStories;
import static edu.hw6.Task5.HackerNews.news;
import static org.assertj.core.api.Assertions.assertThat;

public class HackerNewsTest {
    @Test
    @DisplayName("Получение топа публикаций")
    void testTopStories() {
        long[] actualResult = hackerNewsTopStories();

        assertThat(actualResult).isNotEmpty();
    }

    @Test
    @DisplayName("Извлечение названия")
    void testNewsTitle() throws URISyntaxException {
        String actualResult = news(38311453);

        assertThat(actualResult).isEqualTo("Old engineering simulation games, now open source [pdf]");
    }

    @Test
    @DisplayName("Без названия")
    void testNewsNoTitle() throws URISyntaxException {
        String actualResult = news(38319679);

        assertThat(actualResult).isNull();
    }
}
