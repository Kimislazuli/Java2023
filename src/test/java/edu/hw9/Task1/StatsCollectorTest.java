package edu.hw9.Task1;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StatsCollectorTest {

    @Test

    void correctlyFindStats() {
        StatsCollector collector = new StatsCollector();

        Thread producer = new Thread(() -> {
            for (int j = 0; j < 1000; j++) {
                double[] data = {0.1, 0.2, 0.3};
                collector.push(new Metric("metric_name", data));
            }
        });
        producer.start();

        Thread consumer = new Thread(() -> {
            for (Statistics stat : collector.stats()) {
                assertThat(stat.max()).isCloseTo(0.3, Offset.offset(0.1));
                assertThat(stat.min()).isCloseTo(0.1, Offset.offset(0.1));
                assertThat(stat.average()).isCloseTo(0.2, Offset.offset(0.1));
                assertThat(stat.max()).isCloseTo(600.0, Offset.offset(0.1));
            }
        });

        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        consumer.start();
    }
}
