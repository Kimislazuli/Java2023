package edu.hw10.Task2;

import edu.hw10.Task2.caches.DiskCache;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

public class CacheProxyTest {
    private interface WithoutCachingFib {
        long fib(long number);
    }
    private interface InMemoryCacheFib {
        @Cache(persist = false)
        long fib(long number);
    }

    private interface FileCacheFib {
        @Cache(persist = true)
        long fib(long number);
    }

    @Test
    @DisplayName("Кэширование в хранилище")
    void inMemoryCache() throws IOException {
        final int[] callCounter = {0};
        InMemoryCacheFib fibonacci = new InMemoryCacheFib() {
            public long fib(long number) {
                callCounter[0]++;
                if (number < 0) {
                    throw new IllegalArgumentException("Число должно быть неотрицательным.");
                }
                if (number == 0 || number == 1) {
                    return 1;
                }
                return fib(number - 1) + fib(number - 2);
            }
        };

        InMemoryCacheFib proxiedFibonacci = CacheProxy.create(fibonacci, InMemoryCacheFib.class);
        proxiedFibonacci.fib(1);
        proxiedFibonacci.fib(1);

        assertThat(callCounter[0]).isEqualTo(1);
    }

    @Test
    @DisplayName("Кэширование на диск")
    void diskCache() throws IOException {
        final int[] callCounter = {0};
        FileCacheFib fibonacci = new FileCacheFib() {
            public long fib(long number) {
                callCounter[0]++;
                if (number < 0) {
                    throw new IllegalArgumentException("Число должно быть неотрицательным.");
                }
                if (number == 0 || number == 1) {
                    return 1;
                }
                return fib(number - 1) + fib(number - 2);
            }
        };

        FileCacheFib proxiedFibonacci = CacheProxy.create(fibonacci, FileCacheFib.class);
        proxiedFibonacci.fib(1);
        proxiedFibonacci.fib(1);

        assertThat(callCounter[0]).isEqualTo(1);
    }

    @Test
    @DisplayName("Без кэширования на диск")
    void withoutCache() throws IOException {
        final int[] callCounter = {0};
        WithoutCachingFib fibonacci = new WithoutCachingFib() {
            public long fib(long number) {
                callCounter[0]++;
                if (number < 0) {
                    throw new IllegalArgumentException("Число должно быть неотрицательным.");
                }
                if (number == 0 || number == 1) {
                    return 1;
                }
                return fib(number - 1) + fib(number - 2);
            }
        };

        WithoutCachingFib proxiedFibonacci = CacheProxy.create(fibonacci, WithoutCachingFib.class);
        proxiedFibonacci.fib(1);
        proxiedFibonacci.fib(1);

        assertThat(callCounter[0]).isEqualTo(2);
    }
}
