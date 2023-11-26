package edu.hw7.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class IncrementorTest {
    @Test
    @DisplayName("Многократный инкремент в одном потоке")
    void correctlyWorksWhileSingleThreadIncrementOnce() throws InterruptedException {
        Incrementor incrementor = new Incrementor();

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                incrementor.increment();
            }
        });

        thread.start();
        thread.join();

        int actualResult = incrementor.getCounter().get();

        assertThat(actualResult).isEqualTo(10000);
    }

    @Test
    @DisplayName("Однократный инкремент в одном потоке")
    void correctlyWorksWhileSingleThreadIncrementMultipleTimes() throws InterruptedException {
        Incrementor incrementor = new Incrementor();

        Thread thread = new Thread(incrementor::increment);

        thread.start();
        thread.join();

        int actualResult = incrementor.getCounter().get();

        assertThat(actualResult).isEqualTo(1);
    }

    @Test
    @DisplayName("Многократный инкремент в нескольких потоках")
    void correctlyWorksWhileMultipleThreadsIncrementOnce() throws InterruptedException {
        Incrementor incrementor = new Incrementor();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                incrementor.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                incrementor.increment();
            }
        });

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();

        int actualResult = incrementor.getCounter().get();

        assertThat(actualResult).isEqualTo(20000);
    }

    @Test
    @DisplayName("Однократный инкремент в нескольких потоках")
    void correctlyWorksWhileMultipleThreadsIncrementMultipleTimes() throws InterruptedException {
        Incrementor incrementor = new Incrementor();

        Thread thread1 = new Thread(incrementor::increment);

        thread1.start();
        thread1.join();

        Thread thread2 = new Thread(incrementor::increment);

        thread2.start();
        thread2.join();

        int actualResult = incrementor.getCounter().get();

        assertThat(actualResult).isEqualTo(2);
    }
}
