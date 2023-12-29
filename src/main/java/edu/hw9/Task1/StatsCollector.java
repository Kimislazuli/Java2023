package edu.hw9.Task1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class StatsCollector {
    private final ConcurrentHashMap<String, Statistics> statistics = new ConcurrentHashMap<>();

    private final AtomicInteger addingRightNow = new AtomicInteger(0);

    public void push(Metric metric) {
        addingRightNow.incrementAndGet();
        statistics.compute(metric.name(), (name, stat) -> {
                if (stat == null) {
                    return addNewStat(metric);
                } else {
                    return updateStat(metric, stat);
                }
            }
        );
        addingRightNow.decrementAndGet();
    }

    public List<Statistics> stats() {
        while (addingRightNow.get() != 0) {
        }
        return statistics.values().stream().toList();
    }

    private synchronized Statistics updateStat(Metric metric, Statistics stat) {
        double max = Arrays.stream(metric.values()).max().orElse(0.0);
        if (stat.max() > max) {
            max = stat.max();
        }
        double min = Arrays.stream(metric.values()).min().orElse(0.0);
        if (stat.min() < min) {
            min = stat.min();
        }
        double avg = (Arrays.stream(metric.values()).average().orElse(0.0) + stat.average()) / 2;
        double sum = Arrays.stream(metric.values()).sum() + stat.sum();
        return new Statistics(metric.name(), max, min, avg, sum);
    }

    private synchronized Statistics addNewStat(Metric metric) {
        double max = Arrays.stream(metric.values()).max().orElse(0.0);
        double min = Arrays.stream(metric.values()).min().orElse(0.0);
        double avg = Arrays.stream(metric.values()).average().orElse(0.0);
        double sum = Arrays.stream(metric.values()).sum();
        return new Statistics(metric.name(), max, min, avg, sum);
    }
}
