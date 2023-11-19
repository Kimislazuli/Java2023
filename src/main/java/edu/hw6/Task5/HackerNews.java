package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public final class HackerNews {
    private final static String HACKER_NEWS_URI = "https://hacker-news.firebaseio.com/v0/";
    private final static String TOP_STORIES = "topstories.json";
    private final static String ITEM = "item/%d.json";
    private static final long DEFAULT_TIMEOUT = 10;

    private HackerNews() {
    }

    public static long[] hackerNewsTopStories() {
        try (HttpClient httpClient = newHttpClient()) {
            HttpResponse<String> response = sendHttpRequest(httpClient, new URI(
                HACKER_NEWS_URI + TOP_STORIES));
            return Arrays.stream(
                    response.body()
                        .replaceAll("[\\[\\]]", "")
                        .split(",")
                ).mapToLong(Long::parseLong)
                .toArray();
        } catch (IOException e) {
            return new long[0];
        } catch (InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String news(long id) throws URISyntaxException {
        try (HttpClient httpClient = newHttpClient()) {
            HttpResponse<String> response = sendHttpRequest(httpClient, new URI(
                HACKER_NEWS_URI + String.format(ITEM, id)));
            Pattern pattern = Pattern.compile(".*\"title\":\"(.*)\",\"type\":");
            Matcher matcher = pattern.matcher(response.body());
            if (matcher.find()) {
                return matcher.group(1);
            } else {
                return null;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpResponse<String> sendHttpRequest(HttpClient httpClient, URI uri)
        throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .GET()
            .timeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
            .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
