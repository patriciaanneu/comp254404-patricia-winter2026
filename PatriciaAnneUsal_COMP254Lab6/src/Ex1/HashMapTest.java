package Ex1;

import java.util.Random;

public class HashMapTest {

    public static void main(String[] args) {
        int[] sizes = {10000, 20000, 50000};
        double[] loadFactors = {0.25, 0.50, 0.75};

        for (double lf : loadFactors) {
            System.out.println("\nLoad Factor: " + lf);
            for (int n : sizes) {
                System.out.println("\nSize: " + n);
                testMap(new ChainHashMap<Integer, Integer>(17, 109345121, lf), n, "ChainHashMap");
                testMap(new ProbeHashMap<Integer, Integer>(17, 109345121, lf), n, "ProbeHashMap");
            }
        }
    }

    private static void testMap(AbstractHashMap<Integer, Integer> map, int n, String name) {
        Random rand = new Random();
        int[] keys = new int[n];

        for (int i = 0; i < n; i++) {
            keys[i] = rand.nextInt(Integer.MAX_VALUE);
        }

        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            map.put(keys[i], i);
        }
        long insertTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            map.get(keys[i]);
        }
        long searchTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            map.remove(keys[i]);
        }
        long removeTime = System.nanoTime() - start;

        System.out.println(
                name +
                        " | Insert: " + (insertTime / 1000000.0) + " ms" +
                        " | Search: " + (searchTime / 1000000.0) + " ms" +
                        " | Remove: " + (removeTime / 1000000.0) + " ms"
        );
    }
}
