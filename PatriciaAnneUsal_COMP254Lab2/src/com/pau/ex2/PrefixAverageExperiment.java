package com.pau.ex2;

import java.util.Random;

public class PrefixAverageExperiment {

    //test input
    public static double[] randomArray(int n) {
        double[] x = new double[n];
        Random r = new Random();
        for (int i =0; i < n; i++)
            x[i] = r.nextDouble();
        return x;
    }

    public static void main(String[] args) {
        int n = 5000;       //starting value
        int trials = 5;

        int start = n;  //remember the starting value

        System.out.println("Testing prefixAverage1");
        for (int t = 0; t < trials; t++) {
            double[] x = randomArray(n);

            long startTime = System.currentTimeMillis();
            double[] result = PrefixAverage.prefixAverage1(x);
            long endTime = System.currentTimeMillis();

            long elapsed = endTime - startTime;
            System.out.println("n: " + n + " took " + elapsed + " ms");

            n *= 2; //double the problem size
        }

        System.out.println("Testing prefixAverage2");
        n = start;  //restore n to its start value

        for (int t = 0; t < trials; t++) {
            double[] x = randomArray(n);

            long startTime = System.currentTimeMillis();
            double[] result = PrefixAverage.prefixAverage2(x);
            long endTime = System.currentTimeMillis();

            long elapsed = endTime - startTime;
            System.out.println("n: " + n + " took " + elapsed + " ms");

            n *= 2;
        }
    }
}
