package com.pau.ex3;

import java.util.Random;
import java.util.concurrent.*;

public class UniquenessExperiment {

    private static final double TIME_LIMIT_SECONDS = 60.0;

    // starting n
    private static final int START_N_UNIQUE1 = 100;
    private static final int START_N_UNIQUE2 = 1000;

    // safety cap
    private static final int MAX_N_CAP = 200000;

    private static int[] makeUniqueArray(int n) {
        int[] data = new int[n];
        for (int i = 0; i < n; i++) data[i] = i * 2 + 1;
        shuffle(data, new Random(42));
        return data;
    }

    private static void shuffle(int[] a, Random r) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    private static double timeWithTimeoutSeconds(Runnable task, double timeoutSeconds) {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        Future<?> f = ex.submit(task);

        long start = System.nanoTime();

        try {
            f.get((long) (timeoutSeconds * 1000), TimeUnit.MILLISECONDS);
            long end = System.nanoTime();
            return (end - start) / 1_000_000_000.0;
        }
        catch (TimeoutException e) {
            f.cancel(true);
            return timeoutSeconds + 1.0;
        }
        catch (Exception e) {
            f.cancel(true);
            throw new RuntimeException(e);
        }
        finally {
            ex.shutdownNow();
        }
    }

    private static double timeSecondsUnique1(int n) {
        int[] data = makeUniqueArray(n);
        return timeWithTimeoutSeconds(() -> Uniqueness.unique1(data), TIME_LIMIT_SECONDS);
    }

    private static double timeSecondsUnique2(int n) {
        int[] data = makeUniqueArray(n);
        return timeWithTimeoutSeconds(() -> Uniqueness.unique2(data), TIME_LIMIT_SECONDS);
    }

    private static int findMaxNUnderLimit(
            String label,
            int startN,
            java.util.function.IntToDoubleFunction timeFunc) {

        System.out.println(label);

        int low = 0;
        int high = startN;

        while (high < MAX_N_CAP) {
            double t = timeFunc.applyAsDouble(high);
            System.out.printf("Try n = %,d -> %.3f s%n", high, t);

            if (t <= TIME_LIMIT_SECONDS) {
                low = high;
                high *= 2;
            } else {
                break;
            }
        }

        if (high >= MAX_N_CAP) {
            System.out.printf("Reached cap; returning n = %,d%n", low);
            return low;
        }

        int left = low;
        int right = high;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            double t = timeFunc.applyAsDouble(mid);

            System.out.printf("Mid n = %,d -> %.3f s%n", mid, t);

            if (t <= TIME_LIMIT_SECONDS) {
                left = mid;
            } else {
                right = mid;
            }
        }

        System.out.printf("MAX n within %.0f seconds: %,d%n", TIME_LIMIT_SECONDS, left);
        return left;
    }

    public static void main(String[] args) {

        timeWithTimeoutSeconds(() -> Uniqueness.unique1(makeUniqueArray(1_000)), 2.0);
        timeWithTimeoutSeconds(() -> Uniqueness.unique2(makeUniqueArray(1_000)), 2.0);

        int max1 = findMaxNUnderLimit(
                "unique1",
                START_N_UNIQUE1,
                UniquenessExperiment::timeSecondsUnique1);

        int max2 = findMaxNUnderLimit(
                "unique2",
                START_N_UNIQUE2,
                UniquenessExperiment::timeSecondsUnique2);



        System.out.println("\nResults");
        System.out.printf("unique1 max n <= 60s: %,d%n", max1);
        System.out.printf("unique2 max n <= 60s: %,d%n", max2);
    }
}
