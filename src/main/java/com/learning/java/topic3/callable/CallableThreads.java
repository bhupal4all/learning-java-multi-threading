package com.learning.java.topic3.callable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class CallableThreads {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Sum of 0 to 1000 = " + IntStream.rangeClosed(0, 1000).sum());
        int[] intArray = IntStream.rangeClosed(0, 1000).toArray();

        Callable<Integer> call1 = () -> {
            Integer sum = 0;
            for (int i = 0; i < intArray.length / 2; i++) {
                sum += intArray[i];
            }
            return sum;
        };
        Callable<Integer> call2 = () -> {
            Integer sum = 0;
            for (int i = intArray.length / 2; i < intArray.length; i++) {
                sum += intArray[i];
            }
            return sum;
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<Integer>> futures = executor.invokeAll(Arrays.asList(call1, call2));
        executor.shutdown();

        int sum = 0;
        for (Future<Integer> future : futures) {
            sum += future.get();
        }
        System.out.println("Sum of 0 to 1000 using Callable = " + sum);
    }
}
