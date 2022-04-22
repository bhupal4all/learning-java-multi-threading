package com.learning.java.topic15.streams;

import java.util.stream.IntStream;

public class ParallelStreamExample {
    public static void main(String[] args) {
        System.out.println("Sum Sequential: " + sumSequentialStream());
        System.out.println("Sum Parallel: " + sumParallelStream());
    }

    public static int sumSequentialStream() {
        int sum = IntStream.rangeClosed(0, 50000).sum();
        return sum;
    }

    public static int sumParallelStream() {
        int sum = IntStream.rangeClosed(0, 50000).parallel().sum();
        return sum;
    }
}
