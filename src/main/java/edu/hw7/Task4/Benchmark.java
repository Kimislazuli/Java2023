package edu.hw7.Task4;

import edu.hw7.Task4.multiple_thread_implementation.MultithreadingMonteCarloMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.lang.Math.abs;

@SuppressWarnings({"checkstyle:MultipleStringLiterals", "checkstyle:MagicNumber", "checkstyle:UncommentedMain"})
public final class Benchmark {
    private static final Logger LOGGER = LogManager.getLogger(Benchmark.class);

    private Benchmark() {
    }

    private static void multipleThreadsVersion(long iterations) throws InterruptedException {
        long start = System.nanoTime();
        float result = MultithreadingMonteCarloMethod.approximatePiValue(iterations);
        long finish = System.nanoTime();

        long totalTime = finish - start;

        LOGGER.info(iterations + " iterations with 6 threads time: " + totalTime);
        LOGGER.info("the result is: " + result);
        LOGGER.info("error is: " + abs(Math.PI - result));
    }

    private static void singleThreadVersion(long iterations) {
        long start = System.nanoTime();
        float result = SingleThreadMonteCarloMethod.approximatePiValue(iterations);
        long finish = System.nanoTime();

        long totalTime = finish - start;

        LOGGER.info(iterations + " iterations with one thread time: " + totalTime);
        LOGGER.info("the result is: " + result);
        LOGGER.info("error is: " + abs(Math.PI - result));
    }

   public static void main(String[] args) throws InterruptedException {
        singleThreadVersion(10000000L);
        multipleThreadsVersion(10000000L);

        singleThreadVersion(100000000L);
        multipleThreadsVersion(100000000L);

        singleThreadVersion(1000000000L);
        multipleThreadsVersion(1000000000L);
    }
}
